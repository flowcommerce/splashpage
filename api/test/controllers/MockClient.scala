package controllers

import io.flow.common.v0.models.User
import io.flow.splashpage.v0.{Authorization, Client}
import io.flow.splashpage.v0.errors.{ErrorsResponse, UnitResponse}
import io.flow.splashpage.v0.models.{Geo, GeoForm, Publication, Subscription, SubscriptionForm}
import io.flow.play.clients.{MockAuthorizationClient, MockUserTokensClient}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}
import java.util.concurrent.TimeUnit

trait MockClient extends db.Helpers {

  val DefaultDuration = Duration(5, TimeUnit.SECONDS)

  val port = 9010

  lazy val anonClient = new Client(s"http://localhost:$port")

  lazy val identifiedClient = {
    val user = MockUserTokensClient.makeUser()
    val token = "abcdefghijklmnopqrstuvwxyz"
    MockUserTokensClient.add(user, token = Some(token))
    MockAuthorizationClient.grantAll(user.id.toString)
    new Client(
      s"http://localhost:$port",
      Some(Authorization.Basic(token))
    )
  }
  
  def expectErrors[T](
    f: => Future[T],
    duration: Duration = DefaultDuration
  ): ErrorsResponse = {
    Try(
      Await.result(f, duration)
    ) match {
      case Success(response) => {
        sys.error("Expected function to fail but it succeeded with: " + response)
      }
      case Failure(ex) =>  ex match {
        case e: ErrorsResponse => {
          e
        }
        case e => {
          sys.error(s"Expected an exception of type[ErrorsResponse] but got[$e]")
        }
      }
    }
  }

  def expectNotFound[T](
    f: => Future[T],
    duration: Duration = DefaultDuration
  ) {
    expectStatus(404) {
      Await.result(f, duration)
    }
  }

  def expectNotAuthorized[T](
    f: => Future[T],
    duration: Duration = DefaultDuration
  ) {
    expectStatus(401) {
      Await.result(f, duration)
    }
  }

  def expectStatus(code: Int)(f: => Unit) {
    assert(code >= 400, s"code[$code] must be >= 400")

    Try(
      f
    ) match {
      case Success(response) => {
        org.specs2.execute.Failure(s"Expected HTTP[$code] but got HTTP 2xx")
      }
      case Failure(ex) => ex match {
        case UnitResponse(code) => {
          org.specs2.execute.Success()
        }
        case e => {
          org.specs2.execute.Failure(s"Unexpected error: $e")
        }
      }
    }
  }
}



