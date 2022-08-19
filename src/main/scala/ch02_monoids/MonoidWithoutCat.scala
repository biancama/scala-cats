package ch02_monoids

object MonoidWithoutCat {

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit s: Semigroup[A]): Boolean =
    s.combine(x, s.combine(y, z)) == s.combine(s.combine(x, y), z)

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean =
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)

  def checkIdentity[A](generator: List[A])(monoid: Monoid[A]):Boolean = generator.forall(a=> identityLaw(a)(monoid))

  def checkAssociative[A](generator: List[A])(semigroup: Semigroup[A]):Boolean = {
    val allCombination = for {
      x <- generator
      y <- generator
      z <- generator
    } yield (x, y, z)
    allCombination.forall { (x, y, z) => associativeLaw(x, y, z)(semigroup) }
  }
}
