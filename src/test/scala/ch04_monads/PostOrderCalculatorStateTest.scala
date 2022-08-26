package ch04_monads

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import PostOrderCalculatorState._

class PostOrderCalculatorStateTest extends AnyFlatSpec with Matchers {
  behavior of "PostOrderCalculatorState"

  it should "do a simple result" in {
    evalOne("42").runA(Nil).value should be (42)
  }
  it should "do run a more complex result" in {
    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    } yield ans

    program.runA(Nil).value should be (3)
  }

  it should "do run a even more complex result" in {
    val program = for {
      _ <- evalAll(List("1", "2", "+"))
      _ <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans
    program.runA(Nil).value should be (21)
  }


  it should "do a complex calculation result" in {
    val program = evalAll(List("1", "2", "+", "3", "*"))
    program.runA(Nil).value should be(9)
  }
}
