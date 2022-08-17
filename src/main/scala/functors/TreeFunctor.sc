import cats.Functor

sealed trait Tree[+A]
final case class Branch[+A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[+A](value: A) extends Tree[A]

object Tree {
  implicit val functor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: (A) => B): Tree[B] = fa match {
      case Branch(left: Tree[A], right: Tree[A]) =>
        Branch(map(left)(f), map(right)(f))
      case Leaf(value) =>
        Leaf(f(value))
    }
  }
}
//implicit val treeFunctor: Functor[Tree] = new Functor[Tree]:
//  override def map[A, B](tree: Tree[A])(f: A => B) = tree match {
//    case Leaf(value) => Leaf(f(value))
//    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
//  }


//  val tree = branch(leaf(123), branch(leaf(2), leaf(3)))
//
//  def times10[F[_]](f: F[Int])(implicit func: Functor[F]): F[Int] = func.map(f)(_ * 10)
//  val multiplied = times10(tree)

//Tree.branch(Tree.leaf(10), Tree.branch(Tree.leaf(20), Tree.leaf(30))).map(_ * 2)

val tree: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

tree map (_ + 1)