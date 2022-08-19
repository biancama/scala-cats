package ch02_monoids

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import ch02_monoids.MonoidWithoutCat._
import ch02_monoids.TruthInstances._

class TruthTest extends AnyFlatSpec with Matchers {
  val booleans = List(true, false)


  behavior of "andTruth"

  it should "be complain to identity law" in {
      checkIdentity(booleans)(andMonoid) should be(true)
  }

  it should "be complain to associative law" in {
    checkAssociative(booleans)(andMonoid) should be(true)
  }

  behavior of "orTruth"

  it should "be complain to identity law" in {
    checkIdentity(booleans)(orMonoid) should be(true)
  }

  it should "be complain to associative law" in {
    checkAssociative(booleans)(orMonoid) should be(true)
  }

  behavior of "xorTruth"

  it should "be complain to identity law" in {
    checkIdentity(booleans)(xorMonoid) should be(true)
  }

  it should "be complain to associative law" in {
    checkAssociative(booleans)(xorMonoid) should be(true)
  }

  behavior of "xnorTruth"

  it should "be complain to identity law" in {
    checkIdentity(booleans)(xnorMonoid) should be(true)
  }

  it should "be complain to associative law" in {
    checkAssociative(booleans)(xnorMonoid) should be(true)
  }

}
