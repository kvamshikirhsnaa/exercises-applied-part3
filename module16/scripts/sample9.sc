import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.util.Random

class XorRandom(seed: Long) extends java.util.Random {
  private[this] var _seed = seed

  // not thread safe - need a separate instance for each thread!
  override protected def next(nbits: Int): Int = {
    var x = this._seed
    x ^= (x << 21)
    x ^= (x >>> 35)
    x ^= (x << 4)
    this._seed = x
    x &= ((1L << nbits) - 1)
    x.toInt
  }
}

object XorRandom {
  def random(seed: Long = Random.nextLong()): Random = {
    new Random(new XorRandom(seed))
  }
}

class CalcPi {

  // Make this return a Future[Double] so that these draws can run in parallel
  def draw(iterations: Int): Future[Double] = Future {
    val random = XorRandom.random()
    var ct = 0
    var sum = 0.0

    while (ct < iterations) {
      ct += 1
      val x = random.nextDouble()
      val y = random.nextDouble()
      if (x * x + y * y < 1.0) sum += 1
    }
    sum / iterations
  }

  // Make this method return a Future[Vector[Double]] instead so that the implementation
  // can take advantage of multiple cores
  def draws(n: Int, iterations: Int): Future[Vector[Double]] = {
    Future.traverse((1 to n).toVector)(_ => draw(iterations))

    // or alternatively
    // val drawFutures = for (_ <- 1 to n) yield draw(iterations)
    // Future.sequence(drawFutures.toVector)
  }

  // This method should call the above and combine the Futures in a way that does
  // not block or await them, returning a Future[Double] itself. We will await the
  // result in the test below.
  def calc(n: Int, iterations: Int): Future[Double] = {
    for (xs <- draws(n, iterations)) yield {
      xs.sum / xs.length
    }
  }
}


  val calcPi = new CalcPi
  val pi = Await.result(calcPi.calc(500, 10000000), 1.minute) * 4
