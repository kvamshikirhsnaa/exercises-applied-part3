import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

val success = Future( 2 / 1 )
val failure = Future( 1 / 0 )

Await.ready(success, 1.second)
Await.ready(failure, 1.second)

Await.result(success, 1.second)
// Await.result(failure, 1.second) throws runtime arithmetic exception

success
failure

success.value
failure.value

success.failed
failure.failed

System.currentTimeMillis

def timeIt[A](fn: => A): A = {
  val start = System.currentTimeMillis()
  val a = fn
  println(s"${System.currentTimeMillis() - start} ms")
  a
}


val f1 = Future { Thread.sleep(500); 1 }
val f2 = Future { Thread.sleep(500); 2 }

val f3 = for {
  a <- f1
  b <- f2
} yield a + b

timeIt {
  Await.result(f3, 2.seconds)
}

val f4 = for {
  a <- Future { Thread.sleep(500); 1 }
  b <- Future { Thread.sleep(500); 2 }
} yield a + b

timeIt {
  Await.result(f4, 2.seconds)
}

val fs = Future.successful(10)
val ff = Future.failed(new IllegalArgumentException("nope!"))

val fr = ff.recover {
  case _: IllegalArgumentException => {Thread.sleep(100); 22}
}

fr
Await.ready(fr, 1.second)
Await.result(fr, 1.second)

val ff2 = Future.failed(new IllegalStateException("Again, nope!"))

val fr2 = ff2.recoverWith {
  case _: IllegalStateException => Future.successful(22)
}

fr2
Await.ready(fr2, 1.second)
Await.result(fr2, 1.second)

val fr3 = ff2.recoverWith {
  case _: IllegalArgumentException => Future.successful(22)
}

fr3
Await.ready(fr3, 1.second)
//Await.result(fr3, 1.second)