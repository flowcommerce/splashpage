import play.api.test._
import play.api.test.Helpers._

import org.scalatest.{Matchers, FlatSpec}

class ApplicationSpec extends FlatSpec with Matchers {

  behavior of "Application"

  it should "send 404 on a bad request" in {
    new WithApplication {
      route(FakeRequest(GET, "/boum")) should ===(None)
    }
  }

  it should "render the index page" in {
    new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) should ===(OK) // must equalTo(OK)
      contentType(home) should contain("text/html")
    }
  }
}
