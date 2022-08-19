package ch03_functors

object Imap {
  trait Codec[A] {
    self =>
    def encode(value: A): String

    def decode(value: String): A

    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      def decode(value: String): B = dec(self.decode(value))
      def encode(value: B): String = self.encode(enc(value))
    }
  }

  def encode[A](value: A)(implicit c: Codec[A]): String = c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)

  val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value

      def decode(value: String): String = value
    }

  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)
  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)
  case class Box[A](value: A)

  implicit def boxCodec[A](implicit aCoded: Codec[A]): Codec[Box[A]] =
    aCoded.imap(Box(_), _.value)
    //aCoded.imap(a => Box(a), b => b.value)
}
