package ch01_typeclasses

import org.scalatest.NonImplicitAssertions
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import ch01_typeclasses.PrintableInstances._
import ch01_typeclasses.PrintableSyntax._
class PrintableTest extends AnyFlatSpec with Matchers with NonImplicitAssertions{

  behavior of "Printable"

  it should "format a String" in {
    Printable.format("string") should be ("string")
  }

  it should "format a int" in {
    Printable.format(42) should be("42")
  }

  it should "format a Cat" in {
    val cat = Cat("Duchessa", 3, "black")
    Printable.format(cat) should be("Duchessa is a 3 year old black cat.")
  }
}
