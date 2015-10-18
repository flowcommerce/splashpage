package controllers

import java.util.UUID
import org.scalatestplus.play._

import play.api.libs.ws._
import play.api.test._

class UsersDaoSpec extends PlaySpec {

  import scala.concurrent.ExecutionContext.Implicits.global

  "A User" must {
    "be able to retrieve anonymous user" in {
      1 mustBe 1
      //todo
    }
  }

}
