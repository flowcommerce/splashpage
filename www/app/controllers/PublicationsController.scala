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

object PublicationsController extends Controller {

  import scala.concurrent.ExecutionContext.Implicits.global

  private lazy val anonClient = ApiClient(None).client

  def postSubscribe(publication: Publication) = Action.async { implicit request =>
    val form = publicationForm.bindFromRequest
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
            Conflict(Json.toJson(Map("errors" -> r.errors.map(_.message).mkString(", "))))
          }
        }
      }

    )
    

  }

  case class PublicationData(
    email: String
  )

  val publicationForm = Form(
    mapping(
      "email" -> nonEmptyText
    )(PublicationData.apply)(PublicationData.unapply)
  )

}
