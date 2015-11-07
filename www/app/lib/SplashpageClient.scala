package lib

import io.flow.user.v0.models.User
import io.flow.splashpage.v0.{Authorization, Client}

trait SplashpageClient {

  def newClient(user: Option[User]): Client

}

@javax.inject.Singleton
class DefaultSplashpageClient() extends SplashpageClient {

  def host: String = Config.requiredString("splashpage.api.host")
  def token: String = Config.requiredString("splashpage.api.token")

  override def newClient(user: Option[User]): Client = {
    new Client(
      apiUrl = host,
      auth = Some(
        Authorization.Basic(
          username = token,
        password = None
        )
      )
    )
  }

}
