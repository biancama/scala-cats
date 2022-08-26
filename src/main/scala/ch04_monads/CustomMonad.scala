package ch04_monads
import cats.Monad

import scala.annotation.tailrec
object  CustomMonad {
  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)

  val treeMonad = new Monad[Tree] {
    def flatMap[A, B] (value: Tree[A]) (func: A => Tree[B]): Tree[B] = value match {
      case Leaf(v) => func(v)
      case Branch(left, right) => branch(flatMap(left)(func), flatMap(right)(func))
    }
    def pure[A](a: A): Tree[A] = leaf(a)
    def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] = {

      @tailrec
      def loop(open: List[Tree[Either[A, B]]], closed: List[Tree[B]]): List[Tree[B]] = open match {
        case Branch(l, r):: tail =>
          l match {
            case Branch(_, _) => loop(l :: r :: tail, closed)
            case Leaf(Left(value)) => loop(f(value) :: r :: tail, closed)
            case Leaf(Right(value)) => loop(r :: tail, pure(value):: closed)
          }
        case Leaf(Left(value)) :: tail  => loop(f(value) :: tail, closed)
        case Leaf(Right(value)) :: tail => closed match {
          case x::xs => loop(tail, branch(x, pure(value)) :: xs)
          case Nil => loop(tail, pure(value)::closed)
        }
        case Nil => closed
      }

      loop(List(f(a)), Nil).head
    }
  }



}

object OptionMonad {
  val optionMonad =
    new Monad[Option] {
      def flatMap[A, B](opt: Option[A])
                       (fn: A => Option[B]): Option[B] =
        opt flatMap fn

      def pure[A](opt: A): Option[A] = Some(opt)

        @tailrec
        def tailRecM[A, B](a: A)(fn: A => Option[Either[A, B]]): Option[B] =
          fn(a) match {
            case None => None
            case Some(Left(a1)) => tailRecM(a1)(fn)
            case Some(Right(b)) => Some(b)
          }
    }
}
