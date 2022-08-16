import cats.Monoid
import cats.implicits.catsSyntaxSemigroup

def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty) (_ |+| _)

def add1[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(Monoid[A].empty) (_ |+| _)

def add2[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty) (_ |+| _)


add(List(2,4, 6))

add1(List(Some(2), None, Some(4), None , Some(6)))

add1(List[Option[Int]](Some(1), Some(2), Some(3)))

add2(List(Some(2), None, Some(4), None , Some(6)))


case class Order(totalCost: Double, quantity: Double)

implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
  def empty: Order = Order(0, 0)
  def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
}
add2(List(Order(10.5, 2), Order(20.1, 4)))
