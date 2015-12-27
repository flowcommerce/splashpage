package db

import io.flow.common.v0.models.Error
import io.flow.user.v0.models.User
import io.flow.splashpage.v0.models.{Geo, Publication, Subscription, SubscriptionForm}
import io.flow.play.clients.UserClient
import io.flow.play.postgresql.{AuditsDao, Query, OrderBy, SoftDelete}
import io.flow.play.util.Validation
import anorm._
import play.api.db._
import play.api.Play.current
import play.api.libs.json._
import java.util.UUID

object SubscriptionsDao {

  private[this] val BaseQuery = Query(s"""
    select subscriptions.guid,
           subscriptions.publication,
           subscriptions.email,
           subscriptions.ip_address as subscriptions_geo_ip_address,
           subscriptions.latitude as subscriptions_geo_latitude,
           subscriptions.longitude as subscriptions_geo_longitude,
           ${AuditsDao.creationOnly("subscriptions")}
      from subscriptions
  """)

  private[this] val InsertQuery = """
    insert into subscriptions
    (guid, email, publication, ip_address, latitude, longitude, created_by_guid)
    values
    ({guid}::uuid, {email}, {publication}, {ip_address}, {latitude}, {longitude}, {created_by_guid}::uuid)
  """

  private def stringToTrimmedOption(value: String): Option[String] = {
    value.trim match {
      case "" => None
      case trimmed => Some(trimmed)
    }
  }

  def validate(
    form: SubscriptionForm
  ): Seq[Error] = {
    val publicationErrors = form.publication match {
      case Publication.UNDEFINED(_) => Seq("Publication not found")
      case _ => Seq.empty
    }

    val emailErrors = if (form.email.trim == "") {
      Seq("Email address cannot be empty")

    } else if (!isValidEmail(form.email)) {
      Seq("Please enter a valid email address")

    } else {
      SubscriptionsDao.findAll(
        email = Some(form.email),
        publication = Some(form.publication),
        limit = 1
      ).headOption match {
        case None => Seq.empty
        case Some(_) => Seq("Email is already subscribed")
      }
    }

    val geoErrors = form.geo match {
      case None => {
        Nil
      }
      case Some(geo) => {
        (geo.latitude, geo.longitude) match {
          case (None, None) | (Some(_), Some(_)) => Nil
          case (Some(_), None) => {
            Seq("If specifying latitude, must also specify longitude")
          }
          case (None, Some(_)) => {
            Seq("If specifying longitude, must also specify latitude")
          }
        }
      }
    }

    Validation.errors(publicationErrors ++ emailErrors ++ geoErrors)
  }

  private def isValidEmail(email: String): Boolean = {
    email.indexOf("@") >= 0
  }

  def create(createdBy: Option[User], form: SubscriptionForm): Subscription = {
    val errors = validate(form)
    assert(errors.isEmpty, errors.map(_.message).mkString("\n"))

    val guid = UUID.randomUUID

    DB.withConnection { implicit c =>
      SQL(InsertQuery).on(
        'guid -> guid,
        'publication -> form.publication.toString,
        'email -> form.email.trim,
        'ip_address -> form.geo.flatMap(_.ipAddress).flatMap(stringToTrimmedOption(_)),
        'latitude -> form.geo.flatMap(_.latitude).flatMap(stringToTrimmedOption(_)),
        'longitude -> form.geo.flatMap(_.longitude).flatMap(stringToTrimmedOption(_)),
        'created_by_guid -> createdBy.map(_.guid).getOrElse(UserClient.AnonymousUserGuid)
      ).execute()
    }

    findByGuid(guid).getOrElse {
      sys.error("Failed to create subscription")
    }
  }

  def softDelete(deletedBy: User, subscription: Subscription) {
    SoftDelete.delete("subscriptions", deletedBy.guid, subscription.guid)
  }

  def findByGuid(guid: UUID): Option[Subscription] = {
    findAll(guid = Some(guid), limit = 1).headOption
  }

  def findAll(
    guid: Option[UUID] = None,
    email: Option[String] = None,
    publication: Option[Publication] = None,
    isDeleted: Option[Boolean] = Some(false),
    orderBy: OrderBy = OrderBy("created_at", Some("subscriptions")),
    limit: Long = 25,
    offset: Long = 0
  ): Seq[Subscription] = {
    DB.withConnection { implicit c =>
      BaseQuery.
        uuid("subscriptions.guid", guid).
        text(
          "subscriptions.email",
          email,
          columnFunctions = Seq(Query.Function.Lower),
          valueFunctions = Seq(Query.Function.Lower, Query.Function.Trim)
        ).
        text("subscriptions.publication", publication).
        nullBoolean(s"subscriptions.deleted_at", isDeleted).
        orderBy(orderBy.sql).
        limit(Some(limit)).
        offset(Some(offset)).
        as(
          io.flow.splashpage.v0.anorm.parsers.Subscription.table("subscriptions").*
        )
    }
  }

}
