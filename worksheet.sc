import scala.concurrent._
import duration.Duration
import ExecutionContext.Implicits.global




















case class User(id: Int)
case class Ad(thing: String)

def getUser(id: Int): Future[User] = {
  Future.successful(User(10))
}

def getAds(): Future[List[Ad]] = {
  Future.successful(Nil)
}

def findBestAdsForUser(user: User, ads: List[Ad]): Future[List[Ad]] =
  Future.successful(Nil)

//Whats wrong with this?
result(
  for {
    user <- getUser(5)
    ads <- getAds()
    bestAds <- findBestAdsForUser(user, ads)
  } yield bestAds
)

def result[A](f: => Future[A]) = Await.ready(f, Duration.Inf)