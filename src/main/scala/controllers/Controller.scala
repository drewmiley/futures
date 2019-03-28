package controllers

import connectors.Connector

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

object Controller extends App {

  val id: String = "id1"

  val connector: Connector = Connector

  // Return formatted String with "<name> - <job role> - <salary>

  //TODO implement using maps (Not for yield)
  def getUserDetailsWithMap: Future[String] = ???
  def getUserDetailsWithFor: Future[String] = ???

  // Uncomment these to see the output of your methods respectively
  //getUserDetailsWithMap.map(println)
  //getUserDetailsWithFor.map(println)

}
