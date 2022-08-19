package ch03_functors

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import ch03_functors.Imap.{decode, *}
class ImapTest extends AnyFlatSpec with Matchers {
   behavior of "Imap"

  it should "encode Int" in {
    encode(123) should be("123")
  }

  it should "decode Int" in {
    decode[Int]("123") should be(123)
  }


  it should "encode Double" in {
     encode(123.4) should be ("123.4")
   }

  it should "decode Double" in {
    decode[Double]("123.4") should be(123.4)
  }

  it should "encode Box Double" in {
    encode(Box(123.4)) should be("123.4")
  }

  it should "decode Box Double" in {
    decode[Box[Double]]("123.4") should be(Box(123.4))
  }


}
