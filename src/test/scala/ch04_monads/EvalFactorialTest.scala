package ch04_monads
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class EvalFactorialTest extends AnyFlatSpec with Matchers {
  behavior of "factorial"

  it should "raise a stack  overflow error if not trampolined" in {
    an [StackOverflowError] should be thrownBy factorial(50000)
  }

  behavior of "factorialTrampolined"

  it should "not raise exception because stack safety" in {

    factorialTrampolined(50000).value should be > BigInt(1000)
  }

}
