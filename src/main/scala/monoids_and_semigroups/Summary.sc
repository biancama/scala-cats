import cats.Monoid
import cats.instances.all._
import cats.syntax.semigroup._


Option(1) |+| Option(2)

val map1 = Map("a" -> 1, "b" -> 2)
val map2 = Map("b" -> 3, "d" -> 4)
map1 |+| map2

val tuple1 = ("hello", 123)
val tuple2 = ("world", 321)

tuple1 |+| tuple2

def addAll[A](values: List[A])
             (implicit monoid: Monoid[A]): A =
  values.foldRight(monoid.empty)(_ |+| _)


addAll(List(1, 2, 3))

addAll(List(None, Some(1), Some(2)))