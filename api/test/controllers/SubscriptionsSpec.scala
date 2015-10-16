package controllers

import io.flow.splashpage.v0.{Authorization, Client}
import io.flow.splashpage.v0.errors.ErrorsResponse
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import java.util.UUID

import play.api.libs.ws._
import play.api.test._

class SubscriptionsSpec extends PlaySpecification {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val port = 9010
  lazy val anonClient = new Client(s"http://localhost:$port")
  lazy val systemClient = new Client(
    s"http://localhost:$port",
    Some(Authorization.Basic(db.UsersDao.SystemUserToken))
  )

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

/*
  "POST /subscriptions" in new WithServer(port=port) {
    val form = createSubscriptionForm()
    val subscription = createSubscription(form)
    subscription.email must beEqualTo(form.email)
    subscription.publication must beEqualTo(form.publication)
  }

  "POST /subscriptions validates empty email" in new WithServer(port=port) {
    intercept[ErrorsResponse] {
      createSubscription(createSubscriptionForm().copy(email = "  "))
    }.errors.map(_.message) must be(Seq("Email address cannot be empty"))
  }

  "POST /subscriptions validates duplicate email" in new WithServer(port=port) {
    val form = createSubscriptionForm()
    createSubscription(form)
    intercept[ErrorsResponse] {
      createSubscription(form.copy(email = " " + form.email + " "))
    }.errors.map(_.message) must be(Seq("Email is already subscribed"))

    intercept[ErrorsResponse] {
      createSubscription(form.copy(email = " " + form.email.toUpperCase + " "))
    }.errors.map(_.message) must be(Seq("Email is already subscribed"))

    intercept[ErrorsResponse] {
      createSubscription(form.copy(email = form.email.toLowerCase))
    }.errors.map(_.message) must be(Seq("Email is already subscribed"))
  }

  "POST /subscriptions validates publication" in new WithServer(port=port) {
    val form = createSubscriptionForm()

    intercept[ErrorsResponse] {
      createSubscription(form.copy(publication = Publication.UNDEFINED("invalid_publication")))
    }.errors.map(_.message) must be(Seq("Email is already subscribed"))
  }

  "GET /subscriptions/:guid requires authorization" in new WithServer(port=port) {
    // TODO: Test no token and invalid token
  }
*/

  "GET /subscriptions/:guid" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      systemClient.subscriptions.getByGuid(sub.guid)
    ) must beEqualTo(sub)
  }

  "GET /subscriptions by guid" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      systemClient.subscriptions.get(guid = Some(sub.guid))
    ) must beEqualTo(Seq(sub))

    await(
        systemClient.subscriptions.get(guid = Some(UUID.randomUUID))
    ) must beEqualTo(Nil)
  }

  "GET /subscriptions by email" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      systemClient.subscriptions.get(email = Some(sub.email))
    ) must beEqualTo(Seq(sub))

    await(
      systemClient.subscriptions.get(email = Some(UUID.randomUUID + "@flow.io"))
    ) must beEqualTo(Nil)
  }

  "GET /subscriptions by publication" in new WithServer(port=port) {
    val sub = createSubscription()
    await(
      systemClient.subscriptions.get(email = Some(sub.email), publication = Some(sub.publication))
    ) must beEqualTo(Seq(sub))
  }


}
