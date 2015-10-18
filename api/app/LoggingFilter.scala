import play.api.Logger
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object LoggingFilter extends Filter {

  def apply(
    nextFilter: (RequestHeader) => Future[Result]
  )(
    requestHeader: RequestHeader
  ): Future[Result] = {
    val startTime = System.currentTimeMillis
    nextFilter(requestHeader).map { result =>
      val requestTime = System.currentTimeMillis - startTime
      Logger.info(
        s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}"
      )
      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }

}
