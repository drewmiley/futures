package controllers.userdetails

import connectors.userdetails.UserDetailsConnector

import scala.concurrent.Future

object UserDetailsController {

  val id: String = "id1"

  val connector: UserDetailsConnector = UserDetailsConnector

  // Return formatted String with "<name> - <job role> - <salary>

  //TODO implement using maps (Not for yield)
  def getUserDetailsWithMap: Future[String] = ???
  def getUserDetailsWithFor: Future[String] = ???

}
