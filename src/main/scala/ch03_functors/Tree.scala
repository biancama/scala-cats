package ch03_functors

import cats.Functor
import cats.syntax.functor._
sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]


object Tree {
  implicit val treeFunctor:Functor[Tree] = new Functor[Tree] {
    def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match
      case Leaf(value) => Leaf(f(value))
      case Branch(l, r) => Branch(l map f, r.map(f))
  }

}
