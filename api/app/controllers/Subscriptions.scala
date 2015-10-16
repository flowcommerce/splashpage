package controllers

import db.SubscriptionsDao
import lib.Validation
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import io.flow.splashpage.v0.models.json._
import io.flow.common.v0.models.json._
import play.api.mvc._
import play.api.libs.json._
import java.util.UUID

object Subscriptions extends Controller {

  def get(
    guid: Option[UUID],
    email: Option[String],
    publication: Option[Publication],
    limit: Long = 25,
    offset: Long = 0
  ) = Authenticated { request =>
    request.requireSystem()
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

  def getByGuid(guid: UUID) = Authenticated { request =>
    request.requireSystem()
    Ok(
      Json.toJson(
        SubscriptionsDao.findByGuid(guid)
      )
    )
  }

  def post() = AnonymousRequest(parse.json) { request =>
    request.body.validate[SubscriptionForm] match {
      case e: JsError => {
        BadRequest(Json.toJson(Validation.invalidJson(e)))
      }
      case s: JsSuccess[SubscriptionForm] => {
        val form = s.get
        println(s"Email[${form.email}] subscribing to publication[${form.publication}]")

        SubscriptionsDao.validate(form) match {
          case Nil => {
            val subscription = SubscriptionsDao.create(request.user, form)
            Created(Json.toJson(subscription))
          }
          case errors => {
            Conflict(Json.toJson(errors))
          }
        }
      }
    }
  }

  def deleteByGuid(guid: UUID) = TODO

}
