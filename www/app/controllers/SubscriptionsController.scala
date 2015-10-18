package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import scala.concurrent.Future

import lib.ApiClient
import io.flow.splashpage.v0.models.{Publication, SubscriptionForm}
import io.flow.splashpage.v0.models.json._
import io.flow.common.v0.models.json._
import io.flow.splashpage.v0.errors.ErrorsResponse

object SubscriptionsController extends Controller {

  import scala.concurrent.ExecutionContext.Implicits.global

  private lazy val anonClient = ApiClient(None).client

  def postSubscribe(publication: Publication) = Action.async { implicit request =>
    val form = subscriptionForm.bindFromRequest
    form.fold (

      errors => Future {
        Ok(views.html.index(Some(errors)))
      },

      valid => {
        anonClient.subscriptions.post(
          SubscriptionForm(
            publication = publication,
            email = valid.email
          )
        ).map { sub =>
          Ok(Json.toJson(sub))
        }.recover {
          case r: ErrorsResponse => {
            BadRequest(Json.toJson(r.errors))
          }
        }
      }

    )
    

  }

  case class SubscriptionData(
    email: String
  )

  val subscriptionForm = Form(
    mapping(
      "email" -> nonEmptyText
    )(SubscriptionData.apply)(SubscriptionData.unapply)
  )

}
