package ch04_monads
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import cats.{Id, Monad}
class SumSquareTest extends AnyFlatSpec with Matchers {
  behavior of "sumSquare function"


  it should "sum Int value" in {

    sumSquare(3:Id[Int], 4:Id[Int]) should be(25)

    val a = Monad[Id].pure(3)

    val b = Monad[Id].flatMap(a)(_ + 1)

    sumSquare(a, b) should be(25)
  }

  it should "sum List" in {

    sumSquare(List(1, 2, 3), List(4, 5)) should be(List(17, 26, 20, 29, 25, 34))
  }

  it should "sum option" in {

    sumSquare(Option(3), Option(4)) should be(Some(25))
  }

}
