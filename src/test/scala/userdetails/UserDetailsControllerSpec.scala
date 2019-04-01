package userdetails

import controllers.userdetails.UserDetailsController
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class UserDetailsControllerSpec extends WordSpec with Matchers {

  "getUserDetailsWithMap" should {
    "return the correct string" in {
      Await.result(UserDetailsController.getUserDetailsWithMap, Duration.Inf) shouldBe "Luke Skywalker - Jedi - 0"
    }
  }

  "getUserDetailsWithFor" should {
    "return the correct strring" in {
      Await.result(UserDetailsController.getUserDetailsWithFor, Duration.Inf) shouldBe "Luke Skywalker - Jedi - 0"
    }
  }
}
