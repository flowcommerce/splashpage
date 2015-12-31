package controllers

import db.SubscriptionsDao
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import io.flow.splashpage.v0.models.json._
import io.flow.play.controllers.FlowControllerHelpers
import io.flow.play.util.Validation
import io.flow.postgresql.OrderBy
import io.flow.common.v0.models.json._
import play.api.mvc._
import play.api.libs.json._

class Subscriptions @javax.inject.Inject() () extends Controller with FlowControllerHelpers {

  private[this] val baseContext = "io.flow.authorization.authorizations"
  private[this] val singleContext = "io.flow.authorization.authorizations.one"

  import scala.concurrent.ExecutionContext.Implicits.global

  def get(
    id: Option[Seq[String]],
    email: Option[String],
    publication: Option[Publication],
    limit: Long = 25,
    offset: Long = 0,
    sort: String
  ) = Action { request =>
    sys.error("not yet available pending authorization")

    OrderBy.parse(sort, Some("subscriptions")) match {
      case Left(errors) => {
        UnprocessableEntity(Json.toJson(Validation.invalidSort(errors)))
      }
      case Right(orderBy) => {
        Ok(
          Json.toJson(
            SubscriptionsDao.findAll(
              ids = optionals(id),
              email = email,
              publication = publication,
              limit = limit,
              offset = offset,
              orderBy = orderBy
            )
          )
        )
      }
    }
  }

  def getById(id: String) = Action { request =>
    sys.error("not yet available pending authorization")

    SubscriptionsDao.findById(id) match {
      case None => {
        NotFound
      }
      case Some(sub) => {
        Ok(Json.toJson(sub))
      }
    }
  }

  def post() = Action(parse.json) { request =>
    request.body.validate[SubscriptionForm] match {
      case e: JsError => {
        UnprocessableEntity(Json.toJson(Validation.invalidJson(e)))
      }
      case s: JsSuccess[SubscriptionForm] => {
        val form = s.get
        SubscriptionsDao.findAll(
          publication = Some(form.publication),
          email = Some(form.email)
        ).headOption match {
          case Some(subscription) => {
            Ok(Json.toJson(subscription))
          }
          case None => {
            SubscriptionsDao.create(createdBy = None, form = form) match {
              case Left(errors) => {
                UnprocessableEntity(Json.toJson(Validation.errors(errors)))
              }
              case Right(subscription) => {
                Created(Json.toJson(subscription))
              }
            }
          }
        }
      }
    }
  }

  def deleteById(id: String) = TODO

}
