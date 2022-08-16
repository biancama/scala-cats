import introduction.{JsObject, JsString, Json, JsonWriter, Person}
import introduction.JsonWriterInstances.*
import introduction.JsonSyntax._

Json.toJson(Person("Dave", "dave@example.com"))

Json.toJson(Person("Dave", "dave@example.com"))(personWriter)


Person("Dave", "dave@example.com").toJson