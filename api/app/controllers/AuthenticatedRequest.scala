package controllers

import db.{User, UsersDao}
import play.api.mvc._
import play.api.mvc.Results.Unauthorized
import scala.concurrent.Future
import util.BasicAuthorization

private[controllers] case class UserAuth(user: Option[User])

case class AuthHeaders(
  authorization: Option[String]
)

object AuthHeaders {

  def apply(headers: play.api.mvc.Headers): AuthHeaders = {
    AuthHeaders(
      authorization = headers.get(RequestHelper.AuthorizationHeader)
    )
  }

}

private[controllers] object RequestHelper {

  val AuthorizationHeader = "Authorization"

  def userAuth(authHeaders: AuthHeaders): UserAuth = {
    val user: Option[User] = authHeaders.authorization.flatMap { h =>
      BasicAuthorization.get(authHeaders.authorization) match {
        case Some(auth: BasicAuthorization.Token) => {
          UsersDao.findByToken(auth.token)
        }
        case _ => {
          None
        }
      }
    }
    UserAuth(user)
  }


}

class AnonymousRequest[A](
  val authHeaders: AuthHeaders,
  val user: Option[User],
  request: Request[A]
) extends WrappedRequest[A](request)

object AnonymousRequest extends ActionBuilder[AnonymousRequest] {

  def invokeBlock[A](request: Request[A], block: (AnonymousRequest[A]) => Future[Result]) = {
    val headers = AuthHeaders(request.headers)
    val userAuth = RequestHelper.userAuth(headers)

    block(
      new AnonymousRequest(
        authHeaders = headers,
        user = userAuth.user,
        request = request
      )
    )
  }

}

class AuthenticatedRequest[A](
  val authHeaders: AuthHeaders,
  val user: User,
  request: Request[A]
) extends WrappedRequest[A](request) {

  def requireSystem() {
    require(UsersDao.isSystemUser(user), s"Action requires system role. User[${user.guid}] is not authorized")
  }

}

object Authenticated extends ActionBuilder[AuthenticatedRequest] {

  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[Result]) = {
    val headers = AuthHeaders(request.headers)
    val userAuth = RequestHelper.userAuth(headers)

    userAuth.user match {
      case None => {
        Future.successful(Unauthorized)
      }

      case Some(user) => {
        block(new AuthenticatedRequest(
          authHeaders = headers,
          user,
          request)
        )
      }
    }
  }

}
