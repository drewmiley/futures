package connectors.stockprice

import models.stockprice.Company

import scala.concurrent.Future

trait StockPriceConnector {

  def getCompany(id: String): Future[Company] = {
    Future.successful(Company("At Here"))
  }

  def getStockPrice(company: Company): Future[Int] = {
    Future.successful(12)
  }

  class CompanyNotFoundException extends Exception("Company does not exist")

}

object StockPriceConnector extends StockPriceConnector
