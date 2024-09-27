package controllers.userdetails

import connectors.userdetails.UserDetailsConnector

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object UserDetailsController {

  val id: String = "id1"

  val connector: UserDetailsConnector = UserDetailsConnector

  // Return formatted String with "<name> - <job role> - <salary>

  //TODO implement using maps (Not for yield)
  def getUserDetailsWithMap: Future[String] = {
    val id: String = "id"
    connector.getJobRole(id).flatMap(jobRole => {
      connector.getName(id).flatMap(name => {
        connector.getSalary(name).flatMap(salary => {
          Future.successful(List(name, jobRole, salary.toString).mkString(" - "))
        })
      })
    })
  }

  def getUserDetailsWithFor: Future[String] = {
    val id: String = "id"
    for {
      jobRole <- connector.getJobRole(id)
      name <- connector.getName(id)
      salary <- connector.getSalary(name)
    } yield List(name, jobRole, salary.toString).mkString(" - ")
  }
}
