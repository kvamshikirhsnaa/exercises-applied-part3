def sqrt(x: Double) = {
  def abs(x: Double) = if (x < 0) -x else x
  def sqrtofnum(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtofnum(improve(guess))
  def isGoodEnough(guess: Double) =
    abs(guess * guess - x)/x < 0.001
  def improve(guess: Double) =
    (guess + x/guess)/2
  sqrtofnum(1.0)
}

sqrt(4)
sqrt(100)
sqrt(64)



def pascal(c: Int, r: Int): Int = {
  if (c == 0 || r == 0) 1
  else (if (c > r) 0 else pascal(c - 1, r - 1) + pascal(c, r - 1))
}

println("pascal's traingle")
for (row <- 0 to 10) {
  for (col <- 0 to row)
    print(pascal(col: Int, row: Int) + " ")
    println()
}


var a = 10

try {
  if (a < 0) println("a is -ve")
}
catch {
  case e: NoSuchElementException =>
}
a -= 11



val z = List(1,2,3,4,5,6)

for (x <- z if x % 3 == 0 | x % 2 == 0) yield x

for {
  s <- z
  if s % 3 == 0 || s % 2 == 0
} yield s

for (x <- z) yield x