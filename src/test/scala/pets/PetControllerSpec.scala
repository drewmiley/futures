package pets

import java.io.FileNotFoundException

import connectors.pets.{PetConnector, PetNotFoundException}
import controllers.pets.PetController
import models.pets.{Dog, Pet, Snake}
import org.mockito.ArgumentMatchers
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito.when

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PetControllerSpec extends WordSpec with Matchers with MockitoSugar with ScalaFutures {

  "getPriceOfPet" when {
    "A PetNotFoundException is thrown" should {
      "return 0" in {
        val returnPet = Pet("Name", Dog)
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.successful(returnPet))
        when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
          .thenReturn(Future.failed(new PetNotFoundException))
        whenReady(controller().getPriceOfPet("")) {
          res => res shouldBe 0
        }
      }
    }

    "a successful call has made" when {
      "apply discount is enabled" should {
        "return the discounted price" in {
          val returnPet = Pet("Name", Snake)
          when(mockPetConnector.getPet(ArgumentMatchers.any()))
            .thenReturn(Future.successful(returnPet))
          when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
            .thenReturn(Future.successful(10.0))
          whenReady(controller(true).getPriceOfPet("")) {
            res => res shouldBe 9.0
          }
        }
      }

      "apply discount is disabled" should {
        "return the price" in {
          val returnPet = Pet("Name", Snake)
          when(mockPetConnector.getPet(ArgumentMatchers.any()))
            .thenReturn(Future.successful(returnPet))
          when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
            .thenReturn(Future.successful(10.0))
          whenReady(controller().getPriceOfPet("")) {
            res => res shouldBe 10.0
          }
        }
      }
    }

    "an exception is returned" should {
      "throw that exception" in {
        val returnPet = Pet("Name", Dog)
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.successful(returnPet))
        when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
          .thenReturn(Future.failed(new Exception))

        intercept[Exception] {
          Await.result(controller().getPriceOfPet(""), Duration.Inf)
        }
      }

      "throw that exception (2)" in {
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new FileNotFoundException))
        intercept[FileNotFoundException]{
          Await.result(controller().getPriceOfPet(""), Duration.Inf)
        }
      }
    }
  }

  "getPriceOfPetWithFor" when {
    "A PetNotFoundException is thrown" should {
      "return 0" in {
        val returnPet = Pet("Name", Dog)
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.successful(returnPet))
        when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
          .thenReturn(Future.failed(new PetNotFoundException))
        whenReady(controller().getPriceOfPetWithFor("")) {
          res => res shouldBe 0
        }
      }
    }

    "a successful call has made" when {
      "apply discount is enabled" should {
        "return the discounted price" in {
          val returnPet = Pet("Name", Snake)
          when(mockPetConnector.getPet(ArgumentMatchers.any()))
            .thenReturn(Future.successful(returnPet))
          when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
            .thenReturn(Future.successful(10.0))
          whenReady(controller(true).getPriceOfPetWithFor("")) {
            res => res shouldBe 9.0
          }
        }
      }

      "apply discount is disabled" should {
        "return the price" in {
          val returnPet = Pet("Name", Snake)
          when(mockPetConnector.getPet(ArgumentMatchers.any()))
            .thenReturn(Future.successful(returnPet))
          when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
            .thenReturn(Future.successful(10.0))
          whenReady(controller().getPriceOfPetWithFor("")) {
            res => res shouldBe 10.0
          }
        }
      }
    }

    "an exception is returned" should {
      "throw that exception" in {
        val returnPet = Pet("Name", Dog)
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.successful(returnPet))
        when(mockPetConnector.getPrice(ArgumentMatchers.eq(returnPet)))
          .thenReturn(Future.failed(new Exception))

        intercept[Exception] {
          Await.result(controller().getPriceOfPetWithFor(""), Duration.Inf)
        }
      }

      "throw that exception (2)" in {
        when(mockPetConnector.getPet(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new FileNotFoundException))
        intercept[FileNotFoundException]{
          Await.result(controller().getPriceOfPetWithFor(""), Duration.Inf)
        }
      }
    }
  }

  val mockPetConnector: PetConnector = mock[PetConnector]
  def controller(discount: Boolean = false): PetController = new PetController {
    override val applyDiscount: Boolean = discount
    override val connector: PetConnector = mockPetConnector
  }

}
