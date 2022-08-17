package ch01_typeclasses

import cats.Eq
import cats.implicits.catsSyntaxEq

object EqInstances {
  implicit val catEqual:Eq[Cat] = Eq.instance[Cat] { (c1, c2) =>
    c1.name === c2.name &&
      c1.age === c2.age &&
      c1.color === c2.color
  }
}
