package integration

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.{JsValue, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
  val Patient: JsValue = Json.parse(
    """
   {
      "firstName": "Test",
      "lastName": "Test",
      "passportSN": "Test",
      "phone": "Test",
      "email": "Test",
      "login": "Test",
      "password": "Test"
    }""")
  val BadPatient: JsValue = Json.parse(
    """
   {
      "firstName": "Test",
      "lastName": "Test",
      "passportSN": "Test",
      "phone": "Test",
      "email": "Test",
      "login": "Test"
    }""")

  "Create patient" should {
    "return OK" in {
      val sendRequest = route(app, FakeRequest(POST, controllers.routes.HomeController.createUser().url)
        .withJsonBody(Patient)
      ).get

      status(sendRequest) mustBe OK
    }
    "return BAD_REQUEST" in {
      val sendRequest = route(app, FakeRequest(POST, controllers.routes.HomeController.createUser().url)
        .withJsonBody(BadPatient)
      ).get

      status(sendRequest) mustBe BAD_REQUEST
    }
  }

  "Login page" should {
    "return OK" in {
      val sendRequest = route(app, FakeRequest(POST, controllers.routes.HomeController.adminLogin().url)).get

      status(sendRequest) mustBe OK
    }
  }

  "Registration page" should {
    "return OK" in {
      val sendRequest = route(app, FakeRequest(POST, controllers.routes.HomeController.addPerson().url)).get

      status(sendRequest) mustBe OK
    }
  }

  "Index page" should {
    "return OK" in {
      val sendRequest = route(app, FakeRequest(POST, controllers.routes.HomeController.index().url)).get

      status(sendRequest) mustBe OK
    }
  }

  "Not found page" should {
    "return OK" in {
      val sendRequest = route(app, FakeRequest(POST, "/test")).get

      status(sendRequest) mustBe OK
    }
  }


}
