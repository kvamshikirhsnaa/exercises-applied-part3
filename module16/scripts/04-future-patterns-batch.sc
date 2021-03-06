import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

// batch processing (grouping) futures

val a = (10 to 100 by 10).toVector

val b = a.grouped(3)

b.foreach(println)


def calc(i: Int): Future[Int] = Future {
  println(s"Calculating for $i")
  Thread.sleep(500)
  i * i
}

def processSeq(xs: Vector[Int]): Future[Vector[Int]] = {
  val allFutures: Vector[Future[Int]] = xs.map(calc)
  Future.sequence(allFutures)
}

def processSeq2(xs: Vector[Int]): Future[Vector[Int]] = {
  Future.traverse(xs)(calc)
}

def processSeqBatch(xs: Vector[Int], batchSize: Int): Future[Vector[Int]] = {
  val batches = xs.grouped(batchSize)
  val start = Future.successful(Vector.empty[Int])
  batches.foldLeft(start) {(accF, batch) =>
    for {
      acc <- accF
      batchRes <- processSeq(batch)
    } yield acc ++ batchRes
  }
}

val nums = (1 to 20).toVector

val f = processSeq(nums)

Await.ready(f, 20.seconds)
Await.result(f, 20.seconds)


val f2 = processSeqBatch(nums, 4)

Await.ready(f2, 20.seconds)
Await.result(f2, 20.seconds)
