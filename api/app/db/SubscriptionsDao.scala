package db

import io.flow.common.v0.models.Error
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import anorm._
import lib.Validation
import play.api.db._
import play.api.Play.current
import play.api.libs.json._
import java.util.UUID

object SubscriptionsDao {

  private[this] val BaseQuery = s"""
    select subscriptions.guid,
           subscriptions.publication,
           subscriptions.email,
           ${AuditsDao.queryCreation("subscriptions")}
      from subscriptions
     where true
  """

  private[this] val InsertQuery = """
    insert into subscriptions
    (guid, email, publication, created_by_guid)
    values
    ({guid}::uuid, {email}, {publication}, {created_by_guid}::uuid)
  """

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

    Validation.errors(publicationErrors ++ emailErrors)
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
        'created_by_guid -> createdBy.getOrElse(UsersDao.anonymousUser).guid
      ).execute()
    }

    findByGuid(guid).getOrElse {
      sys.error("Failed to create subscription")
    }
  }

  def softDelete(deletedBy: User, subscription: Subscription) {
    SoftDelete.delete("subscriptions", deletedBy, subscription.guid)
  }

  def findByGuid(guid: UUID): Option[Subscription] = {
    findAll(guid = Some(guid), limit = 1).headOption
  }

  def findAll(
    guid: Option[UUID] = None,
    email: Option[String] = None,
    publication: Option[Publication] = None,
    isDeleted: Option[Boolean] = Some(false),
    limit: Long = 25,
    offset: Long = 0
  ): Seq[Subscription] = {
    val sql = Seq(
      Some(BaseQuery.trim),
      guid.map { v => "and subscriptions.guid = {guid}::uuid" },
      email.map { v => "and lower(subscriptions.email) = lower(trim({email}))" },
      publication.map { v => "and subscriptions.publication = {publication}" },
      isDeleted.map(Filters.isDeleted("subscriptions", _)),
      Some(s"order by subscriptions.created_at limit ${limit} offset ${offset}")
    ).flatten.mkString("\n   ")

    val bind = Seq[Option[NamedParameter]](
      guid.map('guid -> _.toString),
      email.map('email -> _.toString),
      publication.map('publication -> _.toString)
    ).flatten

    DB.withConnection { implicit c =>
      SQL(sql).on(bind: _*)().toList.map { fromRow(_) }.toSeq
    }
  }

  private[db] def fromRow(
    row: anorm.Row
  ): Subscription = {
    Subscription(
      guid = row[UUID]("guid"),
      email = row[String]("email"),
      publication = Publication(row[String]("publication")),
      audit = AuditsDao.fromRowCreation(row)
    )
  }

}
