package ch02_monoids
import MonoidWithoutCat._
object SetInstances {

  implicit def unionSetMonoid[A]:Monoid[Set[A]] = new Monoid [Set[A]]{
    def empty: Set[A] = Set()
    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  implicit def intersectionSetSemigroup[A]: Semigroup[Set[A]] = (x: Set[A], y: Set[A]) => x intersect y
}
