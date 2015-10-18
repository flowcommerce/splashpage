package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent.Future

import lib.ApiClient
import io.flow.splashpage.v0.models.{Publication, SubscriptionForm}
import io.flow.splashpage.v0.errors.ErrorsResponse

object SubscriptionsController extends Controller {

  import scala.concurrent.ExecutionContext.Implicits.global

  private lazy val anonClient = ApiClient(None).client

  def postSubscribe(publication: Publication) = Action.async { implicit request =>
    val form = subscriptionForm.bindFromRequest
    form.fold (

      errors => Future {
        println("Got an invalid form: "+  errors)
        Ok(views.html.index(Some(errors)))
      },

      valid => {
        println("Got a valid form: "+  valid)
        anonClient.subscriptions.post(
          SubscriptionForm(
            publication = publication,
            email = valid.email
          )
        ).map { sub =>
          sys.error("TODO: Success: " + sub)
        }.recover {
          case r: ErrorsResponse => {
            sys.error("TODO: Error: " + r.errors.map(_.message))
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
