package ch04_monads

import cats.data.Reader
import cats.syntax.applicative._

object HackingOnReaders {
  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] = Reader(db => db.passwords.get(username) match {
    case None => false
    case Some(p) => p == password
  })


  def checkLogin(userId: Int, password: String): DbReader[Boolean] = for {
    username <- findUsername(userId)
    check <- username.map(username => checkPassword(username, password)).getOrElse(false.pure[DbReader])
  } yield check
}
