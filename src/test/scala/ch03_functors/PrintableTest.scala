package ch03_functors

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import ch03_functors.Printable._

class PrintableTest extends AnyFlatSpec with Matchers {
 behavior of "Printable"


  it should "have print string" in {
    Printable.format("hello") should be ("\"hello\"")
  }

  it should "have print boolean" in {
    Printable.format(true) should be("yes")
  }

  it should "have print string with box" in {
    Printable.format(Box("hello world")) should be("\"hello world\"")
    Printable.format(Box(true)) should be("yes")
  }

}
