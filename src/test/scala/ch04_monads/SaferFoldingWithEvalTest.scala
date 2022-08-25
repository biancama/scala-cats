package ch04_monads
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import SaferFoldingWithEval._
class SaferFoldingWithEvalTest extends AnyFlatSpec with Matchers {

  behavior of "foldRightWithEval"

  it should "do a sum" in  {
    val inputList = List(1, 2, 3, 4, 5)
    foldRightWithEval(inputList, 0)(_ + _).value should be(15)
  }
}
