package db

import io.flow.play.clients.{MockUserClient, UserClient}
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import java.util.UUID

trait Helpers {

  private val user = MockUserClient.makeUser().copy(guid = UserClient.AnonymousUserGuid)

  def createSubscription(
    form: SubscriptionForm = createSubscriptionForm()
  ): Subscription = {
    SubscriptionsDao.create(Some(user), form)
  }

  def createSubscriptionForm(): SubscriptionForm = {
    SubscriptionForm(
      email = "test-user@" + UUID.randomUUID.toString + ".com",
      publication = Publication.Launch
    )
  }

}

