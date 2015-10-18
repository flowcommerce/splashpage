package lib

import io.flow.splashpage.v0.{Authorization, Client}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
import java.util.UUID

object ApiClient {

  private[this] val unauthenticatedClient = ApiClient(None).client

  def callWith404[T](
    f: Future[T]
  )(implicit ec: ExecutionContext): Future[Option[T]] = {
    f.map {
      value => Some(value)
    }.recover {
      case io.flow.splashpage.v0.errors.UnitResponse(404) => None
      case ex: Throwable => throw ex
    }
  }

  def awaitCallWith404[T](
    future: Future[T]
  )(implicit ec: ExecutionContext): Option[T] = {
    Await.result(
      callWith404(future),
      1000.millis
    )
  }

  /**
    * Blocking call to fetch a user. If the provided guid is not a
    * valid UUID, returns none.
    */
  def getUser(
    guid: String
  )(implicit ec: ExecutionContext): Option[User] = {
    Try(UUID.fromString(guid)) match {
      case Success(userGuid) => {
        // TODO: awaitCallWith404( unauthenticatedClient.users.getByGuid(userGuid) )
        None
      }
      case Failure(ex) => None
    }
  }

}

case class ApiClient(user: Option[User]) {

  private[this] val apiHost = Config.requiredString("splashpage.api.host")
  private[this] val apiAuth = Authorization.Basic(Config.requiredString("splashpage.api.token"))
  private[this] val defaultHeaders = Seq(
    user.map { u =>
      ("X-User-Guid", u.guid.toString)
    }
  ).flatten

  val client: Client = new io.flow.splashpage.v0.Client(
    apiUrl = apiHost,
    auth = Some(apiAuth),
    defaultHeaders = defaultHeaders
  )

}
