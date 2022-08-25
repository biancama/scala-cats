package ch04_monads

import cats.Eval

def fibonacci(n: BigInt): BigInt =
  if(n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)

def fibonacciTrampolined(n: BigInt): Eval[BigInt] = if(n <= 1) Eval.now(n)
else Eval.defer (
      fibonacciTrampolined(n - 1).flatMap(i => fibonacciTrampolined(n-2).map(_ + i))
)


def fibonacciIterative(n: BigInt):BigInt = {
      if (n == 0) 0 else {
            var a = 0;
            var fib_n_1 = 1;
            var fib_n_2 = 0
            for (_ <- n to 2 by -1) {
                  a = fib_n_1 + fib_n_2
                  fib_n_2 = fib_n_1
                  fib_n_1 = a
            }
            fib_n_1
      }
}

def fibonacciTail(n: BigInt): BigInt = {
      def fibonacciTail(a: BigInt, fib_n_1: BigInt, fib_n_2:BigInt): BigInt = {
            println(s"a: ${a} fib_n_1: ${fib_n_1} fib_n_2: ${fib_n_2}")
            if(a <= 1) fib_n_1
            else fibonacciTail(a - 1, fib_n_1 + fib_n_2, fib_n_1)
      }
      if (n == 0) 0 else fibonacciTail(n, 1, 0)
}

def fibonacciTailTrampolined(n: BigInt): Eval[BigInt] = {
      def fibonacciTailTrampolined(a: BigInt, fib_n_1: BigInt, fib_n_2:BigInt): Eval[BigInt] = {
            if(a <= 1) Eval.now(fib_n_1)
            else Eval.defer(fibonacciTailTrampolined(a - 1, fib_n_1 + fib_n_2, fib_n_1).map(x=>x))
      }
      if (n == 0) Eval.now(0) else fibonacciTailTrampolined(n, 1, 0)
}

