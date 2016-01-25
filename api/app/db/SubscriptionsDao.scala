package db

import io.flow.common.v0.models.User
import io.flow.splashpage.v0.models.{Geo, Publication, Subscription, SubscriptionForm}
import io.flow.postgresql.{Query, OrderBy}
import io.flow.play.clients.UserClient
import io.flow.play.util.IdGenerator
import anorm._
import play.api.db._
import play.api.Play.current
import play.api.libs.json._

object SubscriptionsDao {

  private[this] val idGenerator = IdGenerator("sub")

  private[this] val BaseQuery = Query(s"""
    select subscriptions.id,
           subscriptions.publication,
           subscriptions.email,
           subscriptions.ip_address as geo_ip_address,
           subscriptions.latitude as geo_latitude,
           subscriptions.longitude as geo_longitude
      from subscriptions
  """)

  private[this] val InsertQuery = """
    insert into subscriptions
    (id, email, publication, ip_address, latitude, longitude, updated_by_user_id)
    values
    ({id}, {email}, {publication}, {ip_address}, {latitude}, {longitude}, {updated_by_user_id})
  """

  private[this] val SoftDeleteQuery = """
    update subscriptions set deleted_at=now(), updated_by_user_id = {updated_by_user_id} where id = {id}
  """

  private def stringToTrimmedOption(value: String): Option[String] = {
    value.trim match {
      case "" => None
      case trimmed => Some(trimmed)
    }
  }

  private[this] def validate(
    form: SubscriptionForm
  ): Seq[String] = {
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

    publicationErrors ++ emailErrors ++ geoErrors
  }

  private def isValidEmail(email: String): Boolean = {
    email.indexOf("@") >= 0
  }

  def create(createdBy: Option[User], form: SubscriptionForm): Either[Seq[String], Subscription] = {
    validate(form) match {
      case Nil => {
        val id = idGenerator.randomId

        println(s"id: " +id)

        DB.withConnection { implicit c =>
          SQL(InsertQuery).on(
            'id -> id,
            'publication -> form.publication.toString,
            'email -> form.email.trim,
            'ip_address -> form.geo.flatMap(_.ipAddress).flatMap(stringToTrimmedOption(_)),
            'latitude -> form.geo.flatMap(_.latitude).flatMap(stringToTrimmedOption(_)),
            'longitude -> form.geo.flatMap(_.longitude).flatMap(stringToTrimmedOption(_)),
            'updated_by_user_id -> createdBy.map(_.id).getOrElse(UserClient.AnonymousUser.id)
          ).execute()
        }
  
        Right(
          findById(id).getOrElse {
            sys.error("Failed to create subscription")
          }
        )
      }
      case errors => {
        Left(errors)
      }
    }
  }

  def softDelete(deletedBy: User, subscription: Subscription) {
    DB.withConnection { implicit c =>
      SQL(SoftDeleteQuery).on(
        'id -> subscription.id,
        'updated_by_user_id -> deletedBy.id
      ).execute()
    }
  }

  def findById(id: String): Option[Subscription] = {
    findAll(ids = Some(Seq(id)), limit = 1).headOption
  }

  def findAll(
    ids: Option[Seq[String]] = None,
    email: Option[String] = None,
    publication: Option[Publication] = None,
    isDeleted: Option[Boolean] = Some(false),
    orderBy: OrderBy = OrderBy("created_at", Some("subscriptions")),
    limit: Long = 25,
    offset: Long = 0
  ): Seq[Subscription] = {
    DB.withConnection { implicit c =>
      BaseQuery.
        optionalIn("subscriptions.id", ids).
        optionalText(
          "subscriptions.email",
          email,
          columnFunctions = Seq(Query.Function.Lower),
          valueFunctions = Seq(Query.Function.Lower, Query.Function.Trim)
        ).
        optionalText("subscriptions.publication", publication).
        nullBoolean(s"subscriptions.deleted_at", isDeleted).
        orderBy(orderBy.sql).
        limit(limit).
        offset(offset).
        as(
          io.flow.splashpage.v0.anorm.parsers.Subscription.parser().*
        )
    }
  }

}
