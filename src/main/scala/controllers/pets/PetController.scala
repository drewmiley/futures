package controllers.pets

import connectors.pets.PetConnector

import scala.concurrent.Future

trait PetController {

  val connector: PetConnector
  val applyDiscount: Boolean

  //TODO Implement using map and flatMap
  def getPriceOfPet(id: String): Future[Double] = ???


  def getPriceOfPetWithFor(id: String): Future[Double] = ???

}
