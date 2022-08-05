package connectors.pets

import models.pets.Pet

import scala.concurrent.Future

trait PetConnector {
  def getPet(id: String): Future[Pet] = ???

  @throws[PetNotFoundException]("Pet not found")
  def getPrice(pet: Pet): Future[Double] = ???
}

sealed class PetNotFoundException extends Exception("Pet not found")
