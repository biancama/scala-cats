package ch04_monads

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import FactorialWriter.*

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
class FactorialWriterTest extends AnyFlatSpec with Matchers {

  behavior of "normal factory"
  it should "log sequentially" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      factorial(5)
    }
    val expectedOutput = """fact 0 1
                           |fact 1 1
                           |fact 2 2
                           |fact 3 6
                           |fact 4 24
                           |fact 5 120
                           |""".stripMargin

    stream.toString should be (expectedOutput)
  }

  behavior of "writer factory"
  it should "log sequentially" in {

      val futures: Vector[Logged[Int]] = Await.result(Future.sequence(
        Vector(
          Future(factorialWriter(5)),
          Future(factorialWriter(5))
        )
      ), 5.seconds)
    val expectedOutput:String =
      """fact 0 1
        |fact 1 1
        |fact 2 2
        |fact 3 6
        |fact 4 24
        |fact 5 120""".stripMargin

      val logs = futures.map(_.written.mkString("\n"))
      logs should be (Vector(expectedOutput, expectedOutput))
  }
}
