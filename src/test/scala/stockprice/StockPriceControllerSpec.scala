package stockprice

import connectors.stockprice.StockPriceConnector
import controllers.stockprice.StockPriceController
import org.mockito.ArgumentMatchers
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito.{when, reset}
import org.scalatest.{Matchers, WordSpec}
import connectors.stockprice.StockPriceConnector.CompanyNotFoundException
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.Future

class StockPriceControllerSpec extends WordSpec with Matchers with MockitoSugar with ScalaFutures {

  "getStockPrice" should {
    "return could not retrieve stock price" when {
      "CompanyNotFoundException is thrown" in {
        when(mockConnector.getCompany(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new CompanyNotFoundException))

        whenReady(controller.getStockPrice) {
          stockprice => stockprice shouldBe "Company not found"
        }
      }
    }

    "return error" when {
      "an exception is thrown" in {
        when(mockConnector.getCompany(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new Exception("Something went wrong")))

        whenReady(controller.getStockPrice) {
          stockprice => stockprice shouldBe "An error has occurred"
        }
      }
    }

    "return company name and stock price" when {
      "stock price is returned" in {

        whenReady(StockPriceController.getStockPrice) {
          stockprice => stockprice shouldBe "Company: At Here; Stock price: £12"
        }
      }
    }
  }

  "getStockPriceWithFor" should {
    "return could not retrieve stock price" when {
      "CompanyNotFoundException is thrown" in {
        when(mockConnector.getCompany(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new CompanyNotFoundException))

        whenReady(controller.getStockPriceWithFor) {
          stockprice => stockprice shouldBe "Company not found"
        }
      }
    }

    "return error" when {
      "an exception is thrown" in {
        when(mockConnector.getCompany(ArgumentMatchers.any()))
          .thenReturn(Future.failed(new Exception("Something went wrong")))

        whenReady(controller.getStockPriceWithFor) {
         stockprice => stockprice shouldBe "An error has occurred"
        }
      }
    }

    "return company name and stock price" when {
      "stock price is returned" in {
        whenReady(StockPriceController.getStockPriceWithFor) {
          stockprice =>  stockprice shouldBe "Company: At Here; Stock price: £12"
        }
      }
    }
  }

  val mockConnector = mock[StockPriceConnector]

  def controller: StockPriceController = new StockPriceController {
    override val connector: StockPriceConnector = mockConnector
  }

}
