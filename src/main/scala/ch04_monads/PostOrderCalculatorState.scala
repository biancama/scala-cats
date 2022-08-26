package ch04_monads
import cats.data.State
import cats.syntax.applicative.catsSyntaxApplicativeId
object PostOrderCalculatorState {
  type CalcState[A] = State[List[Int], A]

  def merge(state: List[Int], op: (Int, Int) => Int) = {
    val (newState, twoElement) = state.splitAt(state.length - 2)
    newState :+ op(twoElement(0), twoElement(1))
}
  def someTransformation(state: List[Int], s: String): List[Int] = s match {
    case " " => state
    case "+" =>
      merge(state, _ + _)
    case "*" =>
      merge(state, _ * _)
    case _ => state :+ s.toInt
  }
  def someCalculation(s: List[Int]): Int = if (s.size == 1) s.head else 0
  def evalOne(s: String): CalcState[Int] = State[List[Int], Int] { oldStack =>

    val newStack = someTransformation(oldStack, s)
    val result = someCalculation(newStack)
    (newStack, result)
  }

  def evalAll(input: List[String]): CalcState[Int] = input.foldLeft(0.pure[CalcState]) { (state, s) =>
      state.flatMap(_ => evalOne(s))
  }

}
