package ch01_typeclasses

import org.scalatest.NonImplicitAssertions
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import cats.implicits.toShow
import ch01_typeclasses.Show._

class ShowTest extends AnyFlatSpec with Matchers with NonImplicitAssertions {

  behavior of "Show"

  it should "format a String" in {
    // Can't use pretty syntax here,
    // because String already has a format method which conflicts.
    "string".show should be ("string")
  }

  it should "format an Int" in {
    42.show should be ("42")
  }

  it should "format a Cat" in {
    val cat = Cat("Duchessa", 3, "black")
    cat.show should be ("Duchessa is a 3 year old black cat.")
  }
}
