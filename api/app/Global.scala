import io.flow.error.v0.models.json._
import io.flow.play.util.Validation
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.mvc.Results._
import scala.concurrent.Future
import play.api.Play.current

object Global extends WithFilters(LoggingFilter) {

  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(NotFound)
  }

  override def onBadRequest(request: RequestHeader, error: String) = {
    Future.successful(BadRequest(Json.toJson(Validation.serverError(error))))
  }

  override def onError(request: RequestHeader, ex: Throwable) = {
    Logger.error(ex.toString, ex)
    Future.successful(InternalServerError(Json.toJson(Validation.serverError(ex.getMessage))))
  }

}
