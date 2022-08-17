package ch01_typeclasses

import cats.Show
import cats.implicits.toShow
object Show {
  implicit val catShow: Show[Cat] = (t: Cat) => {
    val nameShow = t.name.show
    val ageShow = t.age.show
    val colorShow = t.color.show
    s"${nameShow} is a ${ageShow} year old ${colorShow} cat."
  }
}
