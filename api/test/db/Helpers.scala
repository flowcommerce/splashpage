package db

import io.flow.play.clients.{MockUserTokensClient, UserClient}
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import java.util.UUID

trait Helpers {

  private val user = MockUserTokensClient.makeUser()

  def rightOrErrors[T](result: Either[Seq[String], T]): T = {
    result match {
      case Left(errors) => sys.error(errors.mkString(", "))
      case Right(obj) => obj
    }
  }

  def createSubscription(
    form: SubscriptionForm = createSubscriptionForm()
  ): Subscription = {
    rightOrErrors(SubscriptionsDao.create(Some(user), form))
  }

  def createSubscriptionForm(): SubscriptionForm = {
    SubscriptionForm(
      email = "test-user@" + UUID.randomUUID.toString + ".com",
      publication = Publication.Launch
    )
  }

}

