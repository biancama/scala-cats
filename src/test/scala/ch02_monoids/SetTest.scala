package ch02_monoids

import ch02_monoids.MonoidWithoutCat.{checkAssociative, checkIdentity}
import ch02_monoids.SetInstances.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SetTest extends AnyFlatSpec with Matchers {
  val sets = List(Set(2, 4, 5), Set(1, 2, 3), Set(8, 9, 10))

  behavior of "unionSetMonoid"
  it should "be complain to identity law" in {
    checkIdentity(sets)(unionSetMonoid) should be(true)
  }

  it should "be complain to associative law" in {
    checkAssociative(sets)(unionSetMonoid) should be(true)
  }

  behavior of "intersectionSetMonoid"
  
  it should "be complain to associative law" in {
    checkAssociative(sets)(intersectionSetSemigroup) should be(true)
  }
}
