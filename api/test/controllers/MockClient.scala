package controllers

import io.flow.common.v0.models.UserReference
import io.flow.play.util.AuthHeaders
import io.flow.splashpage.v0.{Authorization, Client}
import io.flow.splashpage.v0.errors.{GenericErrorResponse, UnitResponse}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}
import java.util.concurrent.TimeUnit

trait MockClient extends db.Helpers {

  val DefaultDuration = Duration(5, TimeUnit.SECONDS)

  val port = 9010

  lazy val anonClient = new Client(s"http://localhost:$port")
  lazy val authHeaders = play.api.Play.current.injector.instanceOf[AuthHeaders]

  def identifiedClient(user: UserReference = createUser()): Client = {
    new Client(
      s"http://localhost:$port",
      defaultHeaders = authHeaders.headers(AuthHeaders.user(user))
    )
  }
  
  def expectErrors[T](
    f: => Future[T],
    duration: Duration = DefaultDuration
  ): GenericErrorResponse = {
    Try(
      Await.result(f, duration)
    ) match {
      case Success(response) => {
        sys.error("Expected function to fail but it succeeded with: " + response)
      }
      case Failure(ex) =>  ex match {
        case e: GenericErrorResponse => {
          e
        }
        case e => {
          sys.error(s"Expected an exception of type[GenericErrorResponse] but got[$e]")
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



