package lib

import io.flow.splashpage.v0.{Authorization, Client}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
import java.util.UUID

object ApiClient {

  private[this] val unauthenticatedClient = ApiClient(None).client

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
