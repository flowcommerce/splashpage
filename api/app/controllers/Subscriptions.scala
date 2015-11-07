package controllers

import db.SubscriptionsDao
import io.flow.play.clients.{UserTokenClient, AuthorizationsClient}
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import io.flow.splashpage.v0.models.json._
import io.flow.play.util.Validation
import io.flow.common.v0.models.json._
import play.api.mvc._
import play.api.libs.json._
import java.util.UUID
import scala.concurrent.Future

class Subscriptions @javax.inject.Inject() (
  val userTokensClient: UserTokenClient,
  val authorizationsClient: AuthorizationsClient
) extends Controller
    with io.flow.play.controllers.AuthorizedRestController
{

  private[this] val baseContext = "io.flow.authorization.authorizations"
  private[this] val singleContext = "io.flow.authorization.authorizations.one"

  import scala.concurrent.ExecutionContext.Implicits.global

  def get(
    guid: Option[UUID],
    email: Option[String],
    publication: Option[Publication],
    limit: Long = 25,
    offset: Long = 0
  ) = Authenticated(
    reads = Some(baseContext)
  ) { request =>
    Ok(
      Json.toJson(
        SubscriptionsDao.findAll(
          guid = guid,
          email = email,
          publication = publication,
          limit = limit,
          offset = offset
        )
      )
    )
  }

  def getByGuid(guid: UUID) = Authenticated(
    reads = Some(singleContext)
  ) { request =>
    SubscriptionsDao.findByGuid(guid) match {
      case None => {
        NotFound
      }
      case Some(sub) => {
        Ok(Json.toJson(sub))
      }
    }
  }

  def post() = Anonymous.async(parse.json) { request =>
    request.body.validate[SubscriptionForm] match {
      case e: JsError => Future {
        Conflict(Json.toJson(Validation.invalidJson(e)))
      }
      case s: JsSuccess[SubscriptionForm] => {
        val form = s.get
        SubscriptionsDao.findAll(
          publication = Some(form.publication),
          email = Some(form.email)
        ).headOption match {
          case Some(subscription) => Future {
            Ok(Json.toJson(subscription))
          }
          case None => {
            SubscriptionsDao.validate(form) match {
              case Nil => {
                request.user.map { user =>
                  val subscription = SubscriptionsDao.create(user, form)
                  Created(Json.toJson(subscription))
                }
              }
              case errors => Future {
                Conflict(Json.toJson(errors))
              }
            }
          }
        }
      }
    }
  }

  def deleteByGuid(guid: UUID) = TODO

}
