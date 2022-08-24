package ch04_monads

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EvalFibonacciTest extends AnyFlatSpec with Matchers {
  behavior of "fibonacci"
  it should "be ok for low numbers " in {
    fibonacci(10) should be (55)
  }

  it should "raise a stack  overflow error if not trampolined" in {
    an [StackOverflowError] should be thrownBy fibonacci(50000)
  }

  behavior of "fibonacciTrampolined"

  it should "not raise exception because stack safety" in {

    fibonacciTrampolined(11).value should be > BigInt(55)
  }

  behavior of "fibonacciIterative"
  it should "be ok for low numbers " in {
    fibonacciIterative(10) should be(55)
  }

  it should "be ok for low numbers 2 " in {
    fibonacciIterative(15) should be(610)
  }

  it should "be ok for 0 " in {
    fibonacciIterative(0) should be(0)
  }

  it should "be ok for 1 " in {
    fibonacciIterative(1) should be(1)
  }

  behavior of "fibonacciTail"
  it should "be ok for low numbers " in {
    fibonacciTail(10) should be(55)
  }

  it should "be ok for low numbers 2 " in {
    fibonacciTail(15) should be(610)
  }

  it should "be ok for 0 " in {
    fibonacciTail(0) should be(0)
  }

  it should "be ok for 1 " in {
    fibonacciTail(1) should be(1)
  }

  behavior of "fibonacciTailTrampolined"
  it should "be ok for low numbers " in {
    fibonacciTailTrampolined(10).value should be(55)
  }

  it should "be ok for low numbers 2 " in {
    fibonacciTailTrampolined(15).value should be(610)
  }

  it should "be ok for 0 " in {
    fibonacciTailTrampolined(0).value should be(0)
  }

  it should "be ok for 1 " in {
    fibonacciTailTrampolined(1).value should be(1)
  }



}
