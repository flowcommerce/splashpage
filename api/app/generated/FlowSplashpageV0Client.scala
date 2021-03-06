/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.1.95
 * apidoc:0.11.84 http://www.apidoc.me/flow/splashpage/0.1.95/play_2_4_client
 */
package io.flow.splashpage.v0.models {

  case class Geo(
    country: _root_.scala.Option[String] = None,
    ipAddress: _root_.scala.Option[String] = None
  )

  case class GeoForm(
    country: _root_.scala.Option[String] = None,
    ipAddress: _root_.scala.Option[String] = None
  )

  /**
   * Represents an email address that is currently subscribed to a publication
   */
  case class Subscription(
    id: String,
    email: String,
    publication: io.flow.splashpage.v0.models.Publication,
    geo: io.flow.splashpage.v0.models.Geo
  )

  case class SubscriptionForm(
    email: String,
    publication: io.flow.splashpage.v0.models.Publication,
    geo: _root_.scala.Option[io.flow.splashpage.v0.models.GeoForm] = None
  )

  /**
   * A publication represents something that a user can subscribe to. An example
   * would be subscribing to notification when we launch the business
   */
  sealed trait Publication extends _root_.scala.Product with _root_.scala.Serializable

  object Publication {

    /**
     * Email me when flow launches
     */
    case object Launch extends Publication { override def toString = "launch" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends Publication

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all = Seq(Launch)

    private[this]
    val byName = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): Publication = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[Publication] = byName.get(value.toLowerCase)

  }

}

package io.flow.splashpage.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.flow.error.v0.models.json._
    import io.flow.splashpage.v0.models.json._

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

    implicit val jsonReadsSplashpagePublication = new play.api.libs.json.Reads[io.flow.splashpage.v0.models.Publication] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[io.flow.splashpage.v0.models.Publication] = {
        js match {
          case v: play.api.libs.json.JsString => play.api.libs.json.JsSuccess(io.flow.splashpage.v0.models.Publication(v.value))
          case _ => {
            (js \ "value").validate[String] match {
              case play.api.libs.json.JsSuccess(v, _) => play.api.libs.json.JsSuccess(io.flow.splashpage.v0.models.Publication(v))
              case err: play.api.libs.json.JsError => err
            }
          }
        }
      }
    }

    def jsonWritesSplashpagePublication(obj: io.flow.splashpage.v0.models.Publication) = {
      play.api.libs.json.JsString(obj.toString)
    }

    def jsObjectPublication(obj: io.flow.splashpage.v0.models.Publication) = {
      play.api.libs.json.Json.obj("value" -> play.api.libs.json.JsString(obj.toString))
    }

    implicit def jsonWritesSplashpagePublication: play.api.libs.json.Writes[Publication] = {
      new play.api.libs.json.Writes[io.flow.splashpage.v0.models.Publication] {
        def writes(obj: io.flow.splashpage.v0.models.Publication) = {
          jsonWritesSplashpagePublication(obj)
        }
      }
    }

    implicit def jsonReadsSplashpageGeo: play.api.libs.json.Reads[Geo] = {
      (
        (__ \ "country").readNullable[String] and
        (__ \ "ip_address").readNullable[String]
      )(Geo.apply _)
    }

    def jsObjectGeo(obj: io.flow.splashpage.v0.models.Geo) = {
      (obj.country match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("country" -> play.api.libs.json.JsString(x))
      }) ++
      (obj.ipAddress match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("ip_address" -> play.api.libs.json.JsString(x))
      })
    }

    implicit def jsonWritesSplashpageGeo: play.api.libs.json.Writes[Geo] = {
      new play.api.libs.json.Writes[io.flow.splashpage.v0.models.Geo] {
        def writes(obj: io.flow.splashpage.v0.models.Geo) = {
          jsObjectGeo(obj)
        }
      }
    }

    implicit def jsonReadsSplashpageGeoForm: play.api.libs.json.Reads[GeoForm] = {
      (
        (__ \ "country").readNullable[String] and
        (__ \ "ip_address").readNullable[String]
      )(GeoForm.apply _)
    }

    def jsObjectGeoForm(obj: io.flow.splashpage.v0.models.GeoForm) = {
      (obj.country match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("country" -> play.api.libs.json.JsString(x))
      }) ++
      (obj.ipAddress match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("ip_address" -> play.api.libs.json.JsString(x))
      })
    }

    implicit def jsonWritesSplashpageGeoForm: play.api.libs.json.Writes[GeoForm] = {
      new play.api.libs.json.Writes[io.flow.splashpage.v0.models.GeoForm] {
        def writes(obj: io.flow.splashpage.v0.models.GeoForm) = {
          jsObjectGeoForm(obj)
        }
      }
    }

    implicit def jsonReadsSplashpageSubscription: play.api.libs.json.Reads[Subscription] = {
      (
        (__ \ "id").read[String] and
        (__ \ "email").read[String] and
        (__ \ "publication").read[io.flow.splashpage.v0.models.Publication] and
        (__ \ "geo").read[io.flow.splashpage.v0.models.Geo]
      )(Subscription.apply _)
    }

    def jsObjectSubscription(obj: io.flow.splashpage.v0.models.Subscription) = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "email" -> play.api.libs.json.JsString(obj.email),
        "publication" -> play.api.libs.json.JsString(obj.publication.toString),
        "geo" -> jsObjectGeo(obj.geo)
      )
    }

    implicit def jsonWritesSplashpageSubscription: play.api.libs.json.Writes[Subscription] = {
      new play.api.libs.json.Writes[io.flow.splashpage.v0.models.Subscription] {
        def writes(obj: io.flow.splashpage.v0.models.Subscription) = {
          jsObjectSubscription(obj)
        }
      }
    }

    implicit def jsonReadsSplashpageSubscriptionForm: play.api.libs.json.Reads[SubscriptionForm] = {
      (
        (__ \ "email").read[String] and
        (__ \ "publication").read[io.flow.splashpage.v0.models.Publication] and
        (__ \ "geo").readNullable[io.flow.splashpage.v0.models.GeoForm]
      )(SubscriptionForm.apply _)
    }

    def jsObjectSubscriptionForm(obj: io.flow.splashpage.v0.models.SubscriptionForm) = {
      play.api.libs.json.Json.obj(
        "email" -> play.api.libs.json.JsString(obj.email),
        "publication" -> play.api.libs.json.JsString(obj.publication.toString)
      ) ++ (obj.geo match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("geo" -> jsObjectGeoForm(x))
      })
    }

    implicit def jsonWritesSplashpageSubscriptionForm: play.api.libs.json.Writes[SubscriptionForm] = {
      new play.api.libs.json.Writes[io.flow.splashpage.v0.models.SubscriptionForm] {
        def writes(obj: io.flow.splashpage.v0.models.SubscriptionForm) = {
          jsObjectSubscriptionForm(obj)
        }
      }
    }
  }
}

package io.flow.splashpage.v0 {

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}
    import org.joda.time.{DateTime, LocalDate}
    import org.joda.time.format.ISODateTimeFormat
    import io.flow.splashpage.v0.models._

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

    // Enum: Publication
    private[this] val enumPublicationNotFound = (key: String, e: _root_.java.lang.Exception) => s"Unrecognized $key, should be one of ${io.flow.splashpage.v0.models.Publication.all.mkString(", ")}"

    implicit val pathBindableEnumPublication = new PathBindable.Parsing[io.flow.splashpage.v0.models.Publication] (
      Publication.fromString(_).get, _.toString, enumPublicationNotFound
    )

    implicit val queryStringBindableEnumPublication = new QueryStringBindable.Parsing[io.flow.splashpage.v0.models.Publication](
      Publication.fromString(_).get, _.toString, enumPublicationNotFound
    )

  }

}


package io.flow.splashpage.v0 {

  object Constants {

    val BaseUrl = "https://api.flow.io"
    val Namespace = "io.flow.splashpage.v0"
    val UserAgent = "apidoc:0.11.84 http://www.apidoc.me/flow/splashpage/0.1.95/play_2_4_client"
    val Version = "0.1.95"
    val VersionMajor = 0

  }

  class Client(
    val baseUrl: String = "https://api.flow.io",
    auth: scala.Option[io.flow.splashpage.v0.Authorization] = None,
    defaultHeaders: Seq[(String, String)] = Nil
  ) extends interfaces.Client {
    import io.flow.error.v0.models.json._
    import io.flow.splashpage.v0.models.json._

    private[this] val logger = play.api.Logger("io.flow.splashpage.v0.Client")

    logger.info(s"Initializing io.flow.splashpage.v0.Client for url $baseUrl")

    def subscriptions: Subscriptions = Subscriptions

    object Subscriptions extends Subscriptions {
      override def get(
        id: _root_.scala.Option[Seq[String]] = None,
        email: _root_.scala.Option[String] = None,
        publication: _root_.scala.Option[io.flow.splashpage.v0.models.Publication] = None,
        limit: Long = 25,
        offset: Long = 0,
        sort: String = "-created_at",
        requestHeaders: Seq[(String, String)] = Nil
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.splashpage.v0.models.Subscription]] = {
        val queryParameters = Seq(
          email.map("email" -> _),
          publication.map("publication" -> _.toString),
          Some("limit" -> limit.toString),
          Some("offset" -> offset.toString),
          Some("sort" -> sort)
        ).flatten ++
          id.getOrElse(Nil).map("id" -> _)

        _executeRequest("GET", s"/subscriptions", queryParameters = queryParameters, requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.io.flow.splashpage.v0.Client.parseJson("Seq[io.flow.splashpage.v0.models.Subscription]", r, _.validate[Seq[io.flow.splashpage.v0.models.Subscription]])
          case r if r.status == 401 => throw new io.flow.splashpage.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.splashpage.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401")
        }
      }

      override def getById(
        id: String,
        requestHeaders: Seq[(String, String)] = Nil
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.splashpage.v0.models.Subscription] = {
        _executeRequest("GET", s"/subscriptions/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}", requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.io.flow.splashpage.v0.Client.parseJson("io.flow.splashpage.v0.models.Subscription", r, _.validate[io.flow.splashpage.v0.models.Subscription])
          case r if r.status == 401 => throw new io.flow.splashpage.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.splashpage.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.splashpage.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 401, 404")
        }
      }

      override def post(
        subscriptionForm: io.flow.splashpage.v0.models.SubscriptionForm,
        requestHeaders: Seq[(String, String)] = Nil
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.splashpage.v0.models.Subscription] = {
        val payload = play.api.libs.json.Json.toJson(subscriptionForm)

        _executeRequest("POST", s"/subscriptions", body = Some(payload), requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.io.flow.splashpage.v0.Client.parseJson("io.flow.splashpage.v0.models.Subscription", r, _.validate[io.flow.splashpage.v0.models.Subscription])
          case r if r.status == 201 => _root_.io.flow.splashpage.v0.Client.parseJson("io.flow.splashpage.v0.models.Subscription", r, _.validate[io.flow.splashpage.v0.models.Subscription])
          case r if r.status == 422 => throw new io.flow.splashpage.v0.errors.GenericErrorResponse(r)
          case r => throw new io.flow.splashpage.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 201, 422")
        }
      }

      override def deleteById(
        id: String,
        requestHeaders: Seq[(String, String)] = Nil
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit] = {
        _executeRequest("DELETE", s"/subscriptions/${play.utils.UriEncoding.encodePathSegment(id, "UTF-8")}", requestHeaders = requestHeaders).map {
          case r if r.status == 204 => ()
          case r if r.status == 401 => throw new io.flow.splashpage.v0.errors.UnitResponse(r.status)
          case r if r.status == 404 => throw new io.flow.splashpage.v0.errors.UnitResponse(r.status)
          case r => throw new io.flow.splashpage.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 204, 401, 404")
        }
      }
    }

    def _requestHolder(path: String): play.api.libs.ws.WSRequest = {
      import play.api.Play.current

      val holder = play.api.libs.ws.WS.url(baseUrl + path).withHeaders(
        "User-Agent" -> Constants.UserAgent,
        "X-Apidoc-Version" -> Constants.Version,
        "X-Apidoc-Version-Major" -> Constants.VersionMajor.toString
      ).withHeaders(defaultHeaders : _*)
      auth.fold(holder) {
        case Authorization.Basic(username, password) => {
          holder.withAuth(username, password.getOrElse(""), play.api.libs.ws.WSAuthScheme.BASIC)
        }
        case a => sys.error("Invalid authorization scheme[" + a.getClass + "]")
      }
    }

    def _logRequest(method: String, req: play.api.libs.ws.WSRequest)(implicit ec: scala.concurrent.ExecutionContext): play.api.libs.ws.WSRequest = {
      val queryComponents = for {
        (name, values) <- req.queryString
        value <- values
      } yield s"$name=$value"
      val url = s"${req.url}${queryComponents.mkString("?", "&", "")}"
      auth.fold(logger.info(s"curl -X $method $url")) { _ =>
        logger.info(s"curl -X $method -u '[REDACTED]:' $url")
      }
      req
    }

    def _executeRequest(
      method: String,
      path: String,
      queryParameters: Seq[(String, String)] = Nil,
      requestHeaders: Seq[(String, String)] = Nil,
      body: Option[play.api.libs.json.JsValue] = None
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[play.api.libs.ws.WSResponse] = {
      method.toUpperCase match {
        case "GET" => {
          _logRequest("GET", _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*)).get()
        }
        case "POST" => {
          _logRequest("POST", _requestHolder(path).withHeaders(_withJsonContentType(requestHeaders):_*).withQueryString(queryParameters:_*)).post(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PUT" => {
          _logRequest("PUT", _requestHolder(path).withHeaders(_withJsonContentType(requestHeaders):_*).withQueryString(queryParameters:_*)).put(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PATCH" => {
          _logRequest("PATCH", _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*)).patch(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "DELETE" => {
          _logRequest("DELETE", _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*)).delete()
        }
         case "HEAD" => {
          _logRequest("HEAD", _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*)).head()
        }
         case "OPTIONS" => {
          _logRequest("OPTIONS", _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*)).options()
        }
        case _ => {
          _logRequest(method, _requestHolder(path).withHeaders(requestHeaders:_*).withQueryString(queryParameters:_*))
          sys.error("Unsupported method[%s]".format(method))
        }
      }
    }

    /**
     * Adds a Content-Type: application/json header unless the specified requestHeaders
     * already contain a Content-Type header
     */
    def _withJsonContentType(headers: Seq[(String, String)]): Seq[(String, String)] = {
      headers.find { _._1.toUpperCase == "CONTENT-TYPE" } match {
        case None => headers ++ Seq(("Content-Type" -> "application/json; charset=UTF-8"))
        case Some(_) => headers
      }
    }

  }

  object Client {

    def parseJson[T](
      className: String,
      r: play.api.libs.ws.WSResponse,
      f: (play.api.libs.json.JsValue => play.api.libs.json.JsResult[T])
    ): T = {
      f(play.api.libs.json.Json.parse(r.body)) match {
        case play.api.libs.json.JsSuccess(x, _) => x
        case play.api.libs.json.JsError(errors) => {
          throw new io.flow.splashpage.v0.errors.FailedRequest(r.status, s"Invalid json for class[" + className + "]: " + errors.mkString(" "))
        }
      }
    }

  }

  sealed trait Authorization extends _root_.scala.Product with _root_.scala.Serializable
  object Authorization {
    case class Basic(username: String, password: Option[String] = None) extends Authorization
  }

  package interfaces {

    trait Client {
      def baseUrl: String
      def subscriptions: io.flow.splashpage.v0.Subscriptions
    }

  }

  trait Subscriptions {
    /**
     * Search subscriptions. Always paginated.
     */
    def get(
      id: _root_.scala.Option[Seq[String]] = None,
      email: _root_.scala.Option[String] = None,
      publication: _root_.scala.Option[io.flow.splashpage.v0.models.Publication] = None,
      limit: Long = 25,
      offset: Long = 0,
      sort: String = "-created_at",
      requestHeaders: Seq[(String, String)] = Nil
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[io.flow.splashpage.v0.models.Subscription]]

    /**
     * Returns information about a specific subscription.
     */
    def getById(
      id: String,
      requestHeaders: Seq[(String, String)] = Nil
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.splashpage.v0.models.Subscription]

    /**
     * Create a new subscription.
     */
    def post(
      subscriptionForm: io.flow.splashpage.v0.models.SubscriptionForm,
      requestHeaders: Seq[(String, String)] = Nil
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[io.flow.splashpage.v0.models.Subscription]

    def deleteById(
      id: String,
      requestHeaders: Seq[(String, String)] = Nil
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Unit]
  }

  package errors {

    import io.flow.error.v0.models.json._
    import io.flow.splashpage.v0.models.json._

    case class GenericErrorResponse(
      response: play.api.libs.ws.WSResponse,
      message: Option[String] = None
    ) extends Exception(message.getOrElse(response.status + ": " + response.body)){
      lazy val genericError = _root_.io.flow.splashpage.v0.Client.parseJson("io.flow.error.v0.models.GenericError", response, _.validate[io.flow.error.v0.models.GenericError])
    }

    case class UnitResponse(status: Int) extends Exception(s"HTTP $status")

    case class FailedRequest(responseCode: Int, message: String, requestUri: Option[_root_.java.net.URI] = None) extends _root_.java.lang.Exception(s"HTTP $responseCode: $message")

  }

}