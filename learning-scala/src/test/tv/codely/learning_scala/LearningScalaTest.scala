package tv.codely.learning_scala

import org.scalatest._
import org.scalatest.Matchers._

final class LearningScalaTest extends WordSpec with GivenWhenThen {
  "LearningScala" should {
    "greet" in {
      Given("a LearningScala")

      val learningScala = new LearningScala

      When("we ask him to greet someone")

      val nameToGreet = "CodelyTV"
      val greeting = learningScala.greet(nameToGreet)

      Then("it should say hello to someone")

      greeting shouldBe "Hello " + nameToGreet
    }
  }
}
