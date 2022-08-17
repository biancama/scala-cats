package ch01_typeclasses

trait Printable[A] {
  def format(value: A): String
}

object Printable {

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)
}

object PrintableInstances {
  implicit val printableString: Printable[String] = new Printable[String] {
    def format(value: String): String = value

  }
  implicit val printableInt: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString

  }

  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    override def format(a: Cat): String = {
      val name = Printable.format(a.name)
      val age = Printable.format(a.age)
      val color = Printable.format(a.color)
      s"$name is a $age year old $color cat."
    }
  }
}

object PrintableSyntax {

  implicit class PrintableString[A](a: A)  {
    def format(value: A)(implicit printable: Printable[A]): String = Printable.format(value)
  }

}