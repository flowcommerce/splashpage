package db

import io.flow.common.v0.models.Audit
import io.flow.user.v0.models.User
import io.flow.play.postgresql.{AuditsDao, Filters}
import java.util.UUID
import anorm._
import play.api.db._
import play.api.Play.current

object UsersDao {

  private[db] val SystemEmailAddress = "otto@flow.io"
  private[db] val AnonymousEmailAddress = "anonymous@flow.io"

  private[this] val BaseQuery = s"""
    select users.guid,
           (select array_agg(email) from emails where user_guid=users.guid) as emails,
           ${AuditsDao.queryCreation("users")}
      from users
     where true
  """

  lazy val anonymousUser: User = {
    findAll(email = Some(AnonymousEmailAddress), limit = 1).headOption.getOrElse {
      sys.error(s"Could not find anonymous user[$AnonymousEmailAddress]")
    }
  }

  lazy val systemUser: User = {
    findAll(email = Some(SystemEmailAddress), limit = 1).headOption.getOrElse {
      sys.error(s"Could not find system user[$SystemEmailAddress]")
    }
  }

  def findByToken(token: String): Option[User] = {
    findAll(token = Some(token)).headOption
  }

  def isSystemUser(user: User): Boolean = {
    user.email == Some(SystemEmailAddress)
  }

  def findByEmail(email: String): Option[User] = {
    findAll(email = Some(email), limit = 1).headOption
  }

  def findByGuid(guid: UUID): Option[User] = {
    findAll(guid = Some(guid), limit = 1).headOption
  }

  def findAll(
    guid: Option[UUID] = None,
    email: Option[String] = None,
    token: Option[String] = None,
    isDeleted: Option[Boolean] = Some(false),
    limit: Long = 25,
    offset: Long = 0
  ): Seq[User] = {
    val sql = Seq(
      Some(BaseQuery.trim),
      guid.map { v => "and users.guid = {guid}::uuid" },
      email.map { v => "and users.guid = (select user_guid from emails where lower(email) = lower(trim({email})) and deleted_at is null)" },
      token.map { v => "and users.guid = (select user_guid from tokens where token = trim({token}) and deleted_at is null)" },
      isDeleted.map(Filters.isDeleted("users", _)),
      Some(s"order by users.created_at limit ${limit} offset ${offset}")
    ).flatten.mkString("\n   ")

    val bind = Seq[Option[NamedParameter]](
      guid.map('guid -> _.toString),
      email.map('email -> _.toString),
      token.map('token -> _.toString)
    ).flatten

    DB.withConnection { implicit c =>
      SQL(sql).on(bind: _*)().toList.map { fromRow(_) }.toSeq
    }
  }

  private[db] def fromRow(
    row: anorm.Row
  ): User = {
    User(
      guid = row[UUID]("guid"),
      email = row[List[String]]("emails").toSeq.headOption,
      audit = AuditsDao.fromRowCreation(row)
    )
  }

}

