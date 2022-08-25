package ch04_monads

import cats.Eval

object SaferFoldingWithEval {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRightWithEval[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] = as match {
    case Nil => Eval.now(acc)
    case head :: tail => Eval.defer(foldRightWithEval(tail, acc)(fn).map(res => fn(head, res)))
  }
}
