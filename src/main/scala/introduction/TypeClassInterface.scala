package introduction
// companion object  for Json class
object Json2 {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}