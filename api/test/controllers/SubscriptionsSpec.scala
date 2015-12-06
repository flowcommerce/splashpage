package controllers

import io.flow.splashpage.v0.{Authorization, Client}
import io.flow.splashpage.v0.errors.{ErrorsResponse, UnitResponse}
import io.flow.splashpage.v0.models.{Geo, Publication, Subscription, SubscriptionForm}
import io.flow.user.v0.models.User
import io.flow.play.clients.{MockAuthorizationClient, MockUserClient}
import java.util.UUID
import scala.util.{Failure, Success, Try}

import play.api.libs.ws._
import play.api.test._

class SubscriptionsSpec extends PlaySpecification {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val port = 9010
  lazy val anonClient = new Client(s"http://localhost:$port")
  lazy val identifiedClient = {
    val user = MockUserClient.makeUser()
    val token = "abcdefghijklmnopqrstuvwxyz"
    MockUserClient.add(user, token = Some(token))
    MockAuthorizationClient.grantAll(user.guid)
    new Client(
      s"http://localhost:$port",
      Some(Authorization.Basic(token))
    )
  }

  def createSubscription(
    form: SubscriptionForm = createSubscriptionForm()
  ): Subscription = {
    await(anonClient.subscriptions.post(form))
  }

  def createSubscriptionForm(): SubscriptionForm = {
    SubscriptionForm(
      email = "test-user@" + UUID.randomUUID.toString + ".com",
      publication = Publication.Launch
    )
  }

  def expectErrors(f: => Unit): ErrorsResponse = {
    Try(
      f
    ) match {
      case Success(response) => {
        sys.error("Expected function to fail but it succeeded with: " + response)
      }
      case Failure(ex) =>  ex match {
        case e: ErrorsResponse => {
          e
        }
        case e => {
          sys.error(s"Expected an exception of type[ErrorsResponse] but got[$e]")
        }
      }
    }
  }

  def expectStatus(code: Int, f: => Unit) {
    assert(code >= 400, s"code[$code] must be >= 400")

    Try(
      f
    ) match {
      case Success(response) => {
        org.specs2.execute.Failure(s"Expected HTTP[$code] but got HTTP 200")
      }
      case Failure(ex) => ex match {
        case UnitResponse(code) => {
          org.specs2.execute.Success()
        }
        case e => {
          org.specs2.execute.Failure(s"Expected HTTP[$code] but got HTTP 200")
        }
      }
    }
  }

  "POST /subscriptions" in new WithServer(port=port) {
    val form = createSubscriptionForm()
    val subscription = createSubscription(form)
    subscription.email must beEqualTo(form.email)
    subscription.publication must beEqualTo(form.publication)
  }

  "POST /subscriptions validates empty email" in new WithServer(port=port) {
    expectErrors {
      createSubscription(createSubscriptionForm().copy(email = "  "))
    }.errors.map(_.message) must beEqualTo(Seq("Email address cannot be empty"))
  }

  "POST /subscriptions validates invalid email" in new WithServer(port=port) {
    expectErrors {
      createSubscription(createSubscriptionForm().copy(email = "test"))
    }.errors.map(_.message) must beEqualTo(Seq("Please enter a valid email address"))
  }

  "POST /subscriptions validates duplicate email" in new WithServer(port=port) {
    val form = createSubscriptionForm()
    val sub = createSubscription(form)

    createSubscription(form.copy(email = " " + form.email + " "))
    createSubscription(form.copy(email = " " + form.email.toUpperCase + " "))
    createSubscription(form.copy(email = form.email.toLowerCase))

    await(
      identifiedClient.subscriptions.getByGuid(sub.guid)
    ).email must beEqualTo(form.email)
  }

  "POST /subscriptions validates publication" in new WithServer(port=port) {
    val form = createSubscriptionForm()

    expectErrors {
      createSubscription(form.copy(publication = Publication.UNDEFINED("invalid_publication")))
    }.errors.map(_.message) must beEqualTo(Seq("Publication not found"))
  }

  "POST /subscriptions validates longitude" in new WithServer(port=port) {
    val form = createSubscriptionForm().copy(
      geo = Some(
        Geo(
          latitude = Some("42.213"),
          longitude = None
        )
      )
    )

    expectErrors {
      createSubscription(form)
    }.errors.map(_.message) must beEqualTo(Seq("If specifying latitude, must also specify longitude"))
  }

  "POST /subscriptions validates latitude" in new WithServer(port=port) {
    val form = createSubscriptionForm().copy(
      geo = Some(
        Geo(
          latitude = None,
          longitude = Some("42.213")
        )
      )
    )

    expectErrors {
      createSubscription(form)
    }.errors.map(_.message) must beEqualTo(Seq("If specifying longitude, must also specify latitude"))
  }

  "POST /subscriptions stores geo info" in new WithServer(port=port) {
    val geo = Geo(
      ipAddress = Some("127.0.0.1"),
      latitude = Some("42.213"),
      longitude = Some("12.213")
    )

    val sub = createSubscription(createSubscriptionForm().copy(geo = Some(geo)))
    sub.geo must beEqualTo(Some(geo))
  }

  "POST /subscriptions geo info ignores empty string" in new WithServer(port=port) {
    val geo = Geo(
      ipAddress = Some("  ")
    )

    val sub = createSubscription(createSubscriptionForm().copy(geo = Some(geo)))
    sub.geo must be(None)
  }

  "GET /subscriptions/:guid" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      identifiedClient.subscriptions.getByGuid(sub.guid)
    ) must beEqualTo(sub)
  }

  "GET /subscriptions/:guid requires authorization" in new WithServer(port=port) {
    expectStatus(401,
      await(
        anonClient.subscriptions.getByGuid(UUID.randomUUID)
      )
    )

    val otherClient = new Client(
      s"http://localhost:$port",
      Some(Authorization.Basic(UUID.randomUUID.toString))
    )

    expectStatus(401,
      await(
        anonClient.subscriptions.getByGuid(UUID.randomUUID)
      )
    )
  }

  "GET /subscriptions/:guid w/ invalid guid returns 404" in new WithServer(port=port) {
    expectStatus(404,
      await(
        identifiedClient.subscriptions.getByGuid(UUID.randomUUID)
      )
    )
  }

  "GET /subscriptions by guid" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      identifiedClient.subscriptions.get(guid = Some(sub.guid))
    ) must beEqualTo(Seq(sub))

    await(
        identifiedClient.subscriptions.get(guid = Some(UUID.randomUUID))
    ) must beEqualTo(Nil)
  }

  "GET /subscriptions by email" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      identifiedClient.subscriptions.get(email = Some(sub.email))
    ) must beEqualTo(Seq(sub))

    await(
      identifiedClient.subscriptions.get(email = Some(UUID.randomUUID + "@flow.io"))
    ) must beEqualTo(Nil)
  }

  "GET /subscriptions by publication" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      identifiedClient.subscriptions.get(email = Some(sub.email), publication = Some(sub.publication))
    ) must beEqualTo(Seq(sub))
  }

}
