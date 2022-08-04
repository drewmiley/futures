package controllers.stockprice

import connectors.stockprice.StockPriceConnector
import connectors.stockprice.StockPriceConnector.CompanyNotFoundException

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

trait StockPriceController {

  val connector: StockPriceConnector = StockPriceConnector
  val companyID: String = "companyID1"

  // What happens when the future fails? Can we have different behaviour for different errors?
  def getStockPrice: Future[String] = {
    connector.getCompany(companyID).flatMap(company => {
      connector.getStockPrice(company).flatMap(stockPrice => {
        Future.successful(s"Company: ${company.name}; Stock price: £$stockPrice")
      })
    }).recover {
      case _: CompanyNotFoundException => "Company not found"
      case _: Exception => "An error has occurred"
    }
  }

  def getStockPriceWithFor: Future[String] = {
    val stockPrice: Future[String] = for {
      company <- connector.getCompany(companyID)
      stockPrice <- connector.getStockPrice(company)
    } yield s"Company: ${company.name}; Stock price: £$stockPrice"
    stockPrice recover {
      case _: CompanyNotFoundException => "Company not found"
      case _: Exception => "An error has occurred"
    }
  }

}

object StockPriceController extends StockPriceController
