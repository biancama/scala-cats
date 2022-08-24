package ch04_monads

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GettingFancyTest extends AnyFlatSpec with Matchers {

  behavior of "map"

  it should "should represent sequencing computations without introducing a new Monad" in {

    val simpleMonad = new Monad[List] {
      def pure[A](a: A): List[A] = List(a)
      def flatMap[A, B](value: List[A])(func: A => List[B]): List[B] = value.flatMap(func(_))
    }

      simpleMonad pure(12) should be (List(12))
      simpleMonad.flatMap(List(12, 33))(n => List(n * 2)) should be (List(24, 66))

      simpleMonad.map(List(12, 33))(_ * 2) should be (List(24, 66))
  }

}
