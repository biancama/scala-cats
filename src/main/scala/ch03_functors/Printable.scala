package ch03_functors

trait Printable[A] {
  self: Printable[A] =>   // self-type

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self format func(value)
    }
}

final case class Box[A](value: A)

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)
  implicit val stringPrintable: Printable[String] =
    (value: String) => "\"" + value + "\""
  implicit val booleanPrintable: Printable[Boolean] =
    (value: Boolean) => if (value) "yes" else "no"

  implicit def boxPrintable[A](implicit printable: Printable[A]): Printable[Box[A]] = new Printable[Box[A]]:
    override def format(box: Box[A]): String = printable format box.value
}