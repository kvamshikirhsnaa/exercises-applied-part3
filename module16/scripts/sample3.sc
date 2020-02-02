import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

def calc(i: Int): Future[Int] = Future {
  println(s"Calculating for $i")
  Thread.sleep(100)
  i * i
}

def processSeq(xs: Vector[Int]): Future[Vector[Int]] = {
  val allFutures: Vector[Future[Int]] = xs.map(calc)
  Future.sequence(allFutures)
}

val fa = Future(10)
val fb = Future{ Thread.sleep(100); 20}

val h = for {
  z <- fa
  y <- fb
} yield z + y

Await.ready(h, 1.second)

val z = Future.successful(Vector())
val v = Vector(10,20,30,40,50,60,70,80,90,100)
val fcc = v.grouped(2)


val fc = Future.successful(v)
val e = Future.successful(Vector())
val e2 = Future(Vector())
e2


val fv = processSeq(Vector(10,20,30,40,50))

Await.ready(fv, 1.second)
Await.result(fv, 1.second)
fv


val j = for {
  s <- e
  t <- fv
} yield s ++ t

j
j.value


def processSeqBatch(xs: Vector[Int], batchSize: Int) = {
  val batches = xs.grouped(batchSize)
  val start = Future.successful(Vector.empty[Int])
  batches.foldLeft(start) { (accF, batch) =>
    for {
      acc <- accF
      batchRes <- processSeq(batch)
    } yield acc ++ batchRes
  }
}

val p = (1 to 100).toVector

val res = processSeqBatch(p, 10)

res

Await.ready(res, 10.second)


val findMax = (x: Int, y: Int) => {
  Thread.sleep(10)
  val winner = x max y
  println(s"compared $x to $y, $winner was larger")
  winner
}

List.range(0,20).par

Array.range(0,20).par

val s = List.range(0,10)

s.par

s.par.reduce(findMax)