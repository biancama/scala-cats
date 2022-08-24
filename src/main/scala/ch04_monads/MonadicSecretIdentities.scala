package ch04_monads


object MonadicSecretIdentity {
  type Id[A] = A

  class IdMonad[A] extends Monad[Id] {
    def flatMap[A, B] (value: Id[A]) (func: A => Id[B]): Id[B] = func(value)
    def pure[A](a: A): Id[A] = a
  }

}
