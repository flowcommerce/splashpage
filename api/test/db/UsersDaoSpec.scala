package db

import org.scalatest._
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import java.util.UUID

class UsersDaoSpec extends PlaySpec with OneAppPerSuite {

  import scala.concurrent.ExecutionContext.Implicits.global

  "Special users" must {
    "anonymous user exists" in {
      UsersDao.anonymousUser.emails must be(
        Seq(UsersDao.AnonymousEmailAddress)
      )
    }

    "system user exists" in {
      UsersDao.systemUser.emails must be(
        Seq(UsersDao.SystemEmailAddress)
      )
    }

    "system and anonymous users are different" in {
      UsersDao.AnonymousEmailAddress must not be(
        UsersDao.SystemEmailAddress
      )

      UsersDao.anonymousUser.guid must not be(
        UsersDao.systemUser.guid
      )
    }

    "isSystemUser" in {
      UsersDao.isSystemUser(UsersDao.systemUser) must be(true)
      UsersDao.isSystemUser(UsersDao.anonymousUser) must be(false)
    }

  }

  "findByToken" in {
    UsersDao.findByToken("development").map(_.emails) must be(
      Some(Seq(UsersDao.SystemEmailAddress))
    )
    UsersDao.findByToken(UUID.randomUUID.toString) must be(None)
  }

  "findByEmail" in {
    UsersDao.findByEmail(UsersDao.SystemEmailAddress).map(_.emails) must be(
      Some(Seq(UsersDao.SystemEmailAddress))
    )

    UsersDao.findByEmail(UUID.randomUUID.toString) must be(None)
  }

  "findByGuid" in {
    UsersDao.findByGuid(UsersDao.systemUser.guid).map(_.guid) must be(
      Some(UsersDao.systemUser.guid)
    )

    UsersDao.findByGuid(UUID.randomUUID) must be(None)
  }

}
