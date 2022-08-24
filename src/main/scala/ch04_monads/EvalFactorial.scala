package ch04_monads

import cats.Eval

def factorial(n: BigInt): BigInt =
  if(n == 1) n else n * factorial(n - 1)

def factorialTrampolined(n: BigInt): Eval[BigInt] = if (n == 1) Eval.now(n)
    else Eval.defer (factorialTrampolined(n-1).map(i => i*n))