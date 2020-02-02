import java.util.{Timer, TimerTask}

import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.util.Try
import scala.util.control.NonFatal



var time = 0

def calc(): Int = {
  println(s"time is: $time")
  if (time > 3) 10 else {
    time += 1
    throw new IllegalStateException("not yet")
  }
}



def fCalc: Future[Int] = Future(calc())

def resetTimer = time = 0

val p1 = Promise[Future[Int]]

val f1 = p1.future

f1.flatten

val f2 = p1.success(fCalc)

def retry[T](op: => T, retries: Int): Future[T] = {
  Future(op).recoverWith{
    case NonFatal(_) if retries > 0 =>
      retry(op, retries - 1)
  }
}

val timer = new Timer("retying", true)

def after[T](duration: FiniteDuration)(op: () => Future[T])(
            implicit ec: ExecutionContext): Future[T] = {
  val promise = Promise[Future[T]]
  val futureHandle = promise.future
  val task = new TimerTask {
    override def run() = {
      ec.execute( () => promise.success(op()))
    }
  }
  timer.schedule(task, duration.toMillis)
  futureHandle.flatten
}


// after(500.millis)(fCalc)

def retryBackoff[T](op: => T, backoffs: Seq[FiniteDuration]): Future[T] = {
  Future(op).recoverWith {
    case NonFatal(_) if backoffs.nonEmpty =>
      after(backoffs.head)( () => retryBackoff(op, backoffs.tail))
  }
}


val f5 = retryBackoff(calc(), Seq(200.millis, 200.millis, 100.millis, 100.millis, 100.millis))

Await.result(f5, 20.seconds)
