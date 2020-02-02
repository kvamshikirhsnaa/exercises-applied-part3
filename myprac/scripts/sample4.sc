import scala.annotation.tailrec

class Fraction(val n: Int, val d: Int) {
  override def toString: String = s"$n/$d"

  def result(): Double = n/d.toDouble

  def +(other: Fraction): Fraction = {
    new Fraction((n * other.d) + (d * other.n), d * other.d)
  }
}

val f1 = new Fraction(5,7)
f1.result()

val f2 = new Fraction(2,3)
f2.result()

f1 + f2
f2 + f1



class Rational(a: Int, b: Int) {
  require(b != 0, "denominater shoul not be zero")
  def num = a
  def den = b

  override def toString: String = s"$num/$den"

  def result = num/den.toDouble

  def +(p: Rational): Rational = {
    new Rational((num * p.den) + (den * p.num ), den * p.den)
  }

  def -(q: Rational): Rational = new Rational(-q.num, q.den)
  def less(q: Rational) = this.num * q.den < this.den * q.num

  def max(q: Rational): Rational = {
    if (this less q) q else this
  }

  implicit def +(x: Int): Rational = new Rational(x,1)

}

implicit def add(q2: Rational): Rational = new Rational(-q2.num, q2.den)

val R1 = new Rational(5,7)
R1.result

val R2 = new Rational(2,3)
R2.result

R1 + R2
R2 + R1

R2 - R1
R1- R2

val newR1 = R2 - R1
val newR2 = R1- R2

val newR3 = add(R1)
val newR4 = add(R2)

R1 + newR2
R2 + newR1

R1 + 1


R1 less R2
R2 less R1

R1 max R2
R2 max R1


def fact(n: Int): Int = {
  @tailrec
  def iter(n: Int, result: Int): Int = {
    if (n == 0) result
    else iter(n - 1 , n * result)
  }
  iter(n , 1)
}

fact(5)

fact(10)

fact(50)