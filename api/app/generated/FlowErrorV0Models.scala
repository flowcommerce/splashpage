/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.2.12
 * apidoc:0.11.84 http://www.apidoc.me/flow/error/0.2.12/play_2_x_json
 */
package io.flow.error.v0.models {

  /**
   * An error of some type has occured. The most common error will be validation on
   * input. See messages for details.
   */
  case class GenericError(
    code: io.flow.error.v0.models.GenericErrorCode = io.flow.error.v0.models.GenericErrorCode.GenericError,
    messages: Seq[String]
  )

  sealed trait GenericErrorCode extends _root_.scala.Product with _root_.scala.Serializable

  object GenericErrorCode {

    /**
     * Generic errors are the default type. The accompanying message will provide
     * details on the failure.
     */
    case object GenericError extends GenericErrorCode { override def toString = "generic_error" }
    /**
     * A client error has occurred. This represents a misconfiguration of the client
     */
    case object ClientError extends GenericErrorCode { override def toString = "client_error" }
    /**
     * A server error has occurred. The Flow tech team is automatically notified of all
     * server errors
     */
    case object ServerError extends GenericErrorCode { override def toString = "server_error" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends GenericErrorCode

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all = Seq(GenericError, ClientError, ServerError)

    private[this]
    val byName = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): GenericErrorCode = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[GenericErrorCode] = byName.get(value.toLowerCase)

  }

}

package io.flow.error.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.flow.error.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[v0] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }

    implicit val jsonReadsErrorGenericErrorCode = new play.api.libs.json.Reads[io.flow.error.v0.models.GenericErrorCode] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[io.flow.error.v0.models.GenericErrorCode] = {
        js match {
          case v: play.api.libs.json.JsString => play.api.libs.json.JsSuccess(io.flow.error.v0.models.GenericErrorCode(v.value))
          case _ => {
            (js \ "value").validate[String] match {
              case play.api.libs.json.JsSuccess(v, _) => play.api.libs.json.JsSuccess(io.flow.error.v0.models.GenericErrorCode(v))
              case err: play.api.libs.json.JsError => err
            }
          }
        }
      }
    }

    def jsonWritesErrorGenericErrorCode(obj: io.flow.error.v0.models.GenericErrorCode) = {
      play.api.libs.json.JsString(obj.toString)
    }

    def jsObjectGenericErrorCode(obj: io.flow.error.v0.models.GenericErrorCode) = {
      play.api.libs.json.Json.obj("value" -> play.api.libs.json.JsString(obj.toString))
    }

    implicit def jsonWritesErrorGenericErrorCode: play.api.libs.json.Writes[GenericErrorCode] = {
      new play.api.libs.json.Writes[io.flow.error.v0.models.GenericErrorCode] {
        def writes(obj: io.flow.error.v0.models.GenericErrorCode) = {
          jsonWritesErrorGenericErrorCode(obj)
        }
      }
    }

    implicit def jsonReadsErrorGenericError: play.api.libs.json.Reads[GenericError] = {
      (
        (__ \ "code").read[io.flow.error.v0.models.GenericErrorCode] and
        (__ \ "messages").read[Seq[String]]
      )(GenericError.apply _)
    }

    def jsObjectGenericError(obj: io.flow.error.v0.models.GenericError) = {
      play.api.libs.json.Json.obj(
        "code" -> play.api.libs.json.JsString(obj.code.toString),
        "messages" -> play.api.libs.json.Json.toJson(obj.messages)
      )
    }

    implicit def jsonWritesErrorGenericError: play.api.libs.json.Writes[GenericError] = {
      new play.api.libs.json.Writes[io.flow.error.v0.models.GenericError] {
        def writes(obj: io.flow.error.v0.models.GenericError) = {
          jsObjectGenericError(obj)
        }
      }
    }
  }
}

package io.flow.error.v0 {

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}
    import org.joda.time.{DateTime, LocalDate}
    import org.joda.time.format.ISODateTimeFormat
    import io.flow.error.v0.models._

    // Type: date-time-iso8601
    implicit val pathBindableTypeDateTimeIso8601 = new PathBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    implicit val queryStringBindableTypeDateTimeIso8601 = new QueryStringBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    // Type: date-iso8601
    implicit val pathBindableTypeDateIso8601 = new PathBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    implicit val queryStringBindableTypeDateIso8601 = new QueryStringBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    // Enum: GenericErrorCode
    private[this] val enumGenericErrorCodeNotFound = (key: String, e: _root_.java.lang.Exception) => s"Unrecognized $key, should be one of ${io.flow.error.v0.models.GenericErrorCode.all.mkString(", ")}"

    implicit val pathBindableEnumGenericErrorCode = new PathBindable.Parsing[io.flow.error.v0.models.GenericErrorCode] (
      GenericErrorCode.fromString(_).get, _.toString, enumGenericErrorCodeNotFound
    )

    implicit val queryStringBindableEnumGenericErrorCode = new QueryStringBindable.Parsing[io.flow.error.v0.models.GenericErrorCode](
      GenericErrorCode.fromString(_).get, _.toString, enumGenericErrorCodeNotFound
    )

  }

}
