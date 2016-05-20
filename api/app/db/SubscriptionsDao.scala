package db

import io.flow.common.v0.models.UserReference
import io.flow.splashpage.v0.models.{Geo, Publication, Subscription, SubscriptionForm}
import io.flow.postgresql.{Query, OrderBy}
import io.flow.play.util.{Constants, IdGenerator}
import io.flow.reference.Countries
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
           subscriptions.country as geo_country,
           subscriptions.ip_address as geo_ip_address
      from subscriptions
  """)

  private[this] val InsertQuery = """
    insert into subscriptions
    (id, email, publication, country, ip_address, updated_by_user_id)
    values
    ({id}, {email}, {publication}, {country}, {ip_address}, {updated_by_user_id})
  """

  private[this] val dbHelpers = DbHelpers("subscriptions")

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

    publicationErrors ++ emailErrors
  }

  private def isValidEmail(email: String): Boolean = {
    email.indexOf("@") >= 0
  }

  def create(createdBy: Option[UserReference], form: SubscriptionForm): Either[Seq[String], Subscription] = {
    validate(form) match {
      case Nil => {
        val id = idGenerator.randomId
        val countryCode: Option[String] = form.geo.flatMap(_.country).flatMap(stringToTrimmedOption(_).map(_.toLowerCase))
        val flowCountry: Option[String] = countryCode.map { code =>
          Countries.find(code).map(_.iso31663).getOrElse(code)
        }

        DB.withConnection { implicit c =>
          SQL(InsertQuery).on(
            'id -> id,
            'publication -> form.publication.toString,
            'email -> form.email.trim,
            'country -> flowCountry,
            'ip_address -> form.geo.flatMap(_.ipAddress).flatMap(stringToTrimmedOption(_)),
            'updated_by_user_id -> createdBy.map(_.id).getOrElse(Constants.AnonymousUser.id)
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

  def softDelete(deletedBy: UserReference, subscription: Subscription) {
    dbHelpers.delete(deletedBy, subscription.id)
  }

  def findById(id: String): Option[Subscription] = {
    findAll(ids = Some(Seq(id)), limit = 1).headOption
  }

  def findAll(
    ids: Option[Seq[String]] = None,
    email: Option[String] = None,
    publication: Option[Publication] = None,
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
        orderBy(orderBy.sql).
        limit(limit).
        offset(offset).
        as(
          io.flow.splashpage.v0.anorm.parsers.Subscription.parser().*
        )
    }
  }

}
