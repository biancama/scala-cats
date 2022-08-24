package ch04_monads

import cats.Monad
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.Id
def sumSquare[M[_] : Monad](a: M[Int], b: M[Int]): M[Int] =
  for {
    x <- a
    y <- b
  } yield x*x + y*y