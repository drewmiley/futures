package controllers.pets

import connectors.pets.{PetConnector, PetNotFoundException}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait PetController {

  val connector: PetConnector = PetConnector
  val applyDiscount: Boolean = true
  val petId = "1"

  //TODO Implement using map and flatMap
  def getPriceOfPet(id: String): Future[Double] = {
    connector.getPet(petId).flatMap(pet => {
      connector.getPrice(pet).flatMap(price => {
        Future.successful(1D)
      })
    }).recover {
      case _: PetNotFoundException => 1D
      case _: Exception => 1D
    }
  }


  def getPriceOfPetWithFor(id: String): Future[Double] = {
    val petPrice: Future[Double] = for {
      pet <- connector.getPet(petId)
      price <- connector.getPrice(pet)
    } yield price
    petPrice recover {
      case _: PetNotFoundException => 1
      case _: Exception => 1
    }
  }

}

object PetController extends PetController
