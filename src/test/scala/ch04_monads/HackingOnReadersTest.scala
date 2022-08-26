package ch04_monads

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import HackingOnReaders._
class HackingOnReadersTest extends AnyFlatSpec with Matchers {
  behavior of "checkLogin"
  val db = Db(
    Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    ),
    Map(
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )
  )
  it should "return true if credentials are correct" in {
    checkLogin(1, "zerocool").run(db) should be (true)

  }

  it should "return false if credentials are correct" in {
    checkLogin(4, "davinci").run(db) should be(false)
  }

}
