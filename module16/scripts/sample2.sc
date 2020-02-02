import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

val fa: Future[Any] = Future(10)

val fi = fa.collect {
  case i: Int => i
}

val ffi = fi.filter(_ > 11)

Await.ready(fi, 1.second).value
Await.ready(ffi, 1.second).value

val ffit = ffi.transform(i => i * 5, {ex =>
  println(ex.getMessage)
  throw new RuntimeException("it failed to filter", ex)
})

ffit
ffit.fallbackTo(Future.successful(0))

// Transform takes 2 function, when a future suceed first one will execute
// when future fails 2nd one will execute


val f6 = Future(2)

val f7 = f6.andThen {
  case Success(i) if i % 2 == 0 => println(s"it's even")
}

Await.ready(f7, 1.second)
Await.result(f7, 1.second)

f7.foreach(i => println(s"Got an $i"))
f7.failed.foreach(ex => println(ex.getMessage))

val f8 = ffit.andThen {
  case Failure(ex) => println("failed")
}

Await.ready(f8, 1.second)
//Await.result(f8, 1.second)

val z = f7.onComplete {
  case Success(i) => println(s"It worked, and the answer is $i")
  case Failure(ex) => println(s"It failed: ${ex.getMessage}")
}


// z is not Future, we can't do future operations



val nums = List(1,2,3,4,5)
def square(i: Int): Future[Int] = Future(i * i)

val futs: List[Future[Int]] = nums.map(square)
val futList = Future.sequence(futs)
Await.ready(futList, 1.second)


val futList2 = Future.traverse(nums)(square)
Await.ready(futList2, 1.second)

val ft1 = Future { Thread.sleep(10); 10 }
val ft2 = Future { Thread.sleep(5); 5 }
val ft3 = Future { Thread.sleep(20); 20 }

val sft = List(ft1, ft2, ft3)

//Await.ready(Future.firstCompletedOf(sft), 1.second)

//Await.ready(Future.foldLeft(futs)(0)(_ + _), 1.second)
//
//Await.ready(Future.reduceLeft(sft)(_ + _), 1.second)
//
//
//Await.ready(Future.fromTry(Try(1 / 0)), 1.second)




