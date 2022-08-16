import cats.syntax.show.*
import cats.Show


import java.util.Date

123.show

/**
 *  without companion
implicit val dateShow: Show[Date] = new Show[Date]:
  override def show(t: Date) = {
    import java.text.SimpleDateFormat
    val parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
    parser.format(t)
  }
 */
  implicit val dateShow1: Show[Date] = Show.show(date => {
    import java.text.SimpleDateFormat
    val parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    parser.format(date)
  })
val now = new Date()

now.show


final case class Cat(name: String, age: Int, color: String)

implicit val catShow: Show[Cat] = Show.show(cat => {
  val name = cat.name.show
  val age = cat.age.show
  val color = cat.color.show
  s"$name is a $age year-old $color cat."
})



val duchessa = Cat("Duchessa", 3, "Black")
duchessa.show