import cats.Monoid
import monoids_and_semigroups.{associativeLaw, identityLaw}


implicit val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
  def combine(x: Boolean, y: Boolean): Boolean = x && y
  def empty = true

}

implicit val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
  def combine(x: Boolean, y: Boolean): Boolean = x || y
  def empty = true
}

implicit val xorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
  def combine(x: Boolean, y: Boolean): Boolean = x ^ y
  def empty = false
}

implicit val norMonoid: Monoid[Boolean] = new Monoid[Boolean] {
  def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
  def empty = true
}

associativeLaw(true, false, true)(andMonoid)

identityLaw(true)(andMonoid)

identityLaw(false)(andMonoid)


associativeLaw(false, false, true)(xorMonoid)

identityLaw(true)(xorMonoid)

identityLaw(false)(xorMonoid)


associativeLaw(true, false, true)(norMonoid)

identityLaw(true)(norMonoid)

identityLaw(false)(norMonoid)