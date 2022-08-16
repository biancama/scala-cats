package introduction

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = (value: String) => JsString(value)

  implicit val personWriter: JsonWriter[Person] = p => {
    JsObject(Map(
      "name" -> JsString(p.name),
      "email" -> JsString(p.email)
    ))
  }
}