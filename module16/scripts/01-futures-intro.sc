import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

val f1 = Future { Thread.sleep(1000); 10}
val f2 = f1.map(_ * 10)

// 1000ms = 1s

f1.value
f1.isCompleted
f2.value
f2.isCompleted

f1
f2

println("we are not blocked")
println("we can still do stuff")

Thread.sleep(1000)

f1
f2

f1.value
f1.isCompleted

f2.value
f2.isCompleted

val f3 = Future { 1 / 0 }
Thread.sleep(10)
f3.value

val a = 1
val b = 2
val c = 3
val s = "The answer is"

val sum = a + b + c
s"$s $sum"

val fa = Future(1)
val fb = Future{ Thread.sleep(1500); b}
val fc = Future(c)
val fs = Future(s)

val fRes = for {
  a <- fa
  b <- fb
  c <- fc
  s <- fs
} yield {
  val sum = a + b + c
  s"$s $sum"
}

fRes

fRes.isCompleted
fRes.value

Thread.sleep(1000)

fRes.isCompleted
fRes.value

fa
fb
fc
fs

fa.value
fb.value
fc.value
fs.value

val d = for {
  z <- fa
} yield z

val g = for {
  y <- fb
} yield y

d
g

val h = for {
  z <- fa
  y <- fb
} yield z + y

