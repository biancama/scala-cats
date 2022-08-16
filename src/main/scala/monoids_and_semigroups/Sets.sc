import cats.{Monoid, Semigroup}
import monoids_and_semigroups.{associativeLaw, identityLaw}

implicit def unionOnSets[A]: Monoid[Set[A]] = new Monoid[Set[A]] {

  def empty: Set[A] = Set()

  def combine(x: Set[A], y: Set[A]): Set[A] = x | y
}

implicit def intersectionOnSets[A]: Semigroup[Set[A]] = (x: Set[A], y: Set[A]) => x & y

associativeLaw(Set(2), Set(3), Set(4, 5))(unionOnSets)

identityLaw(Set(4, 5))(unionOnSets)


