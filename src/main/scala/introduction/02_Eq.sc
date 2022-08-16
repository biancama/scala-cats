import cats.Eq
import cats.implicits.catsSyntaxEq
final case class Cat(name: String, age: Int, color: String)

val cat1 = Cat("Garfield", 38, "orange and black")
val cat2 = Cat("Heathcliff", 33, "orange and black")
val cat3 = Cat("Garfield", 38, "orange and black")
val optionCat1 = Option(cat1)
val optionCat2 = Option.empty[Cat]
val optionCat3 = Option(cat3)


implicit val catEq: Eq[Cat] = Eq.instance[Cat] {(cat1, cat2) => {
  cat1.name === cat2.name &&
  cat1.age === cat2.age &&
    cat1.color === cat2.color
}}


cat1 === cat2

optionCat1 === optionCat2

cat1 === cat3

optionCat1 === optionCat3
