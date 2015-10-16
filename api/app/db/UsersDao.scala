package db

import java.util.UUID

case class User(guid: UUID)

// Placeholder
object UsersDao {

   
  val Anonymous = User(guid = UUID.fromString("85ae1119-4c98-4738-8204-c899b77c971b"))
  val System = User(guid = UUID.fromString("97098f36-a6fe-4fdd-aa29-00632988bd83"))

  private val systemUserToken = "TODO"

  def findByToken(token: String): Option[User] = {
    token match {
      case `systemUserToken` => Some(System)
      case _ => None
    }
  }

  def isSystemUser(user: User): Boolean = {
    user.guid == System.guid
  }

}
