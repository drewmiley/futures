package controllers.stockprice

import connectors.stockprice.StockPriceConnector
import connectors.stockprice.StockPriceConnector.CompanyNotFoundException

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

trait StockPriceController {

  val connector: StockPriceConnector = StockPriceConnector
  val companyID: String = "companyID1"

  // What happens when the future fails? Can we have different behaviour for different errors?
  def getStockPrice: Future[String] = ???

  def getStockPriceWithFor: Future[String] = ???

}

object StockPriceController extends StockPriceController
