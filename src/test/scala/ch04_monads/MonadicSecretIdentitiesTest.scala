package ch04_monads

import ch04_monads.MonadicSecretIdentity.{Id, IdMonad}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MonadicSecretIdentitiesTest extends AnyFlatSpec with Matchers {
  behavior of "Monad[Id]"
  val id:Id[Int] = 3
  val monadId = new IdMonad[Int]

  it should "do pure function" in {
    monadId.pure(id) should be (3)
  }

  it should "do flat map function" in {
    monadId.flatMap(id)(n => monadId.pure(n + 1 )) should be(4)
  }

  it should "do  map function" in {
    monadId.map(id)(n => n + 1) should be(4)
  }
}
