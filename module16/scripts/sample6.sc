import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

case class TrackInt(v: Int) {
  val log = collection.mutable.Buffer.empty[Int]
  def plus(that: TrackInt) = {
    this.log += that.v
    that.log += this.v
    new TrackInt(this.v + that.v)
  }
}

(1 to 10).map(TrackInt(_))

val xs = (1 to 10).map(TrackInt(_)).par
val zero = TrackInt(0)

xs.foldLeft(zero)(_ plus _)

zero.log

//zero.log.clear()
//
//xs.fold(zero)(_ plus _)
//
//zero.log

val c1 = TrackInt(0)
c1.log

val c2 = TrackInt(1)
c2.log


val s1 = c1 plus c2
s1.log
c1.log
c2.log

val c3 = TrackInt(2)
c3.log

val s2 = s1 plus c3

c1.log
c2.log
c3.log
s1.log
s2.log

val c4 = TrackInt(4)

val s3 = s2 plus c4
c1.log
c2.log
c3.log
s1.log
s2.log
s3.log

val c5 = TrackInt(5)

val s4 = s3 plus c5
c1.log
c2.log
c3.log
s1.log
s2.log
s3.log
s4.log


