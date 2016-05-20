package db

import io.flow.common.v0.models.UserReference
import io.flow.play.util.IdGenerator
import io.flow.splashpage.v0.models.{Publication, Subscription, SubscriptionForm}
import java.util.UUID

trait Helpers {

  private[this] lazy val user = createUser()
  val idGenerator = IdGenerator("tst")

  def createTestId(): String = {
    idGenerator.randomId()
  }

  def createTestName(): String = {
    s"Z Test ${createTestId}"
  }

  def createTestEmail(): String = {
    createTestId() + "@test.flow.io"
  }

  def createUser(): UserReference = {
    UserReference(
      id = createTestId()
    )
  }

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

