package controllers.pets

import connectors.pets.{PetConnector, PetNotFoundException}

import java.io.FileNotFoundException
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait PetController {

  val connector: PetConnector = PetConnector
  val applyDiscount: Boolean
  val petId = "1"

  //TODO Implement using map and flatMap
  def getPriceOfPet(id: String): Future[Double] = {
    connector.getPet(petId).flatMap(pet => {
      connector.getPrice(pet).flatMap(price => {
        Future.successful(if (applyDiscount) 0.9 * price else price)
      })
    }).recover {
      case _: PetNotFoundException => 0
      case _: FileNotFoundException => throw new FileNotFoundException()
    }
  }


  def getPriceOfPetWithFor(id: String): Future[Double] = {
    val petPrice: Future[Double] = for {
      pet <- connector.getPet(petId)
      price <- connector.getPrice(pet)
    } yield if (applyDiscount) 0.9 * price else price
    petPrice recover {
      case _: PetNotFoundException => 0
      case _: FileNotFoundException => throw new FileNotFoundException()
    }
  }

}
