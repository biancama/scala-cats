package ch04_monads
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import CustomMonad._
class BranchingOutFurtherTest extends AnyFlatSpec with Matchers {

  val someLeaf: Tree[Int] = leaf(1)
  val someBranch: Tree[Int] = branch(leaf(1), branch(Leaf(2), leaf(3)))

  def duplicate[A](a: A): Tree[A] =
    Branch(Leaf(a), Leaf(a))


  behavior of "TreeMonad"

  it should "work on Leaf" in {
    val expected = Branch(Leaf(1), Leaf(1))
    val actual = treeMonad.flatMap(someLeaf)(duplicate)
    actual should be(expected)
  }

  it should "work on Branch" in {
    val expected = Branch(Branch(Leaf(1), Leaf(1)), Branch(Branch(Leaf(2), Leaf(2)), Branch(Leaf(3), Leaf(3))))
    val actual = treeMonad.flatMap(someBranch)(duplicate)
    actual should be(expected)
  }
  it should "have Functor-like behaviour" in {
    val expected = Branch(Leaf(2), Branch(Leaf(3), Leaf(4)))
    val actual = treeMonad.map(someBranch)(_ + 1)
    actual should be(expected)
  }

  it should "support for-comprehensions when the monad is in the implicit scope" in {
    import cats.syntax.flatMap._
    import cats.syntax.functor._
    implicit val tm = treeMonad
    val t = for {
      a <- someLeaf
      b <- someBranch
    } yield a + b
    t should be(Branch(Leaf(2), Branch(Leaf(3), Leaf(4))))
  }

  it should "support for-comprehensions when the monad is in the implicit scope(2)" in {
    import cats.syntax.flatMap._
    import cats.syntax.functor._
    implicit val tm = treeMonad
    val t = for {
      a <- branch(leaf(100), leaf(200))
      b <- branch(leaf(a - 10), leaf(a + 10))
      c <- branch(leaf(b - 1), leaf(b + 1))
    } yield c
    t should be(Branch(Branch(Branch(Leaf(89),Leaf(91)),Branch(Leaf(109),Leaf(111))),Branch(Branch(Leaf(189),Leaf(191)),Branch(Leaf(209),Leaf(211)))))
  }
}
