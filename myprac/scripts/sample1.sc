import scala.annotation.tailrec

def fibonacci(n: Int) = {
  var f = 0
  var s = 1
  print(f + "\t" + s)
  var res = 0
  for (x <- 3 to n) {
    res = f + s
    f = s
    s = res
    print("\t" + res)
  }
}

fibonacci(5)

fibonacci(8)

fibonacci(10)

fibonacci(-5)


def fact(n: Int): Int = {
  var res = 1
  for (x <- n to 1 by -1) {
    res = res * x
  }
  res
}

fact(0)
fact(5)
fact(50)
fact(-5)

def factor(n: Int): Int = {
  if (n == 0) 1
  else n * factor(n - 1)
}

factor(0)
factor(5)
factor(50)

def factorial(n: Int): Int = {
  @tailrec
  def iter(n: Int, result: Int): Int = {
    if (n == 0) result
    else iter(n - 1, result * n)
  }
  iter( n, 1)
}

factorial(0)
factorial(5)
factorial(7)
factorial(50)


def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

def fact1(n: Int): Int = product(x => x)(1, n)

product(x => x)(2,3)
fact1(5)
product(x => x)(2,5)
fact1(7)
fact1(50)


def mapreduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
  if (a > b) zero
  else combine(f(a), mapreduce(f, combine,zero)(a + 1, b))
}


def product1(f: Int => Int)(a: Int, b: Int) = mapreduce(f, (a,b) => (a * b), 1)(a, b)

product1(x => x)(1,4)
product1(x => x)(2,4)
product1(x => x)(2,6)
product1(x => x)(1,5)

product1(x => x)(10,15)

mapreduce(x => x, (a,b) => a * b, 1)(1,4)


//(1, (2, (3, (4,1))))


def gcd(a: Int, b: Int): Int = {
  if (b == 0) a else gcd(b, a % b)
}

gcd(14,21)

gcd(120,700)

gcd(700, 120)

def sum(f: Int => Int, a: Int, b: Int): Int = {
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)
}

sum(x => x, 2, 5)
sum(x => x * x, 2, 5)
sum(x => x * x * x, 2, 5)

def sum2(f: Int => Int, a: Int, b: Int) = {
  @tailrec
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  }
  loop(a, 0)
}

sum2(x => x, 2,5)






