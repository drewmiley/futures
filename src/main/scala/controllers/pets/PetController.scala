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
      connector.getPrice(pet).flatMap(Future.successful)
    }).recover {
      case _: PetNotFoundException => 0
      case _: Exception => 0
    }
  }


  def getPriceOfPetWithFor(id: String): Future[Double] = {
    val petPrice: Future[Double] = for {
      pet <- connector.getPet(petId)
      price <- connector.getPrice(pet)
    } yield price
    petPrice recover {
      case _: PetNotFoundException => 0
      case _: Exception => 0
    }
  }

}

object PetController extends PetController
