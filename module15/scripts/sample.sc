
import scala.collection.JavaConverters._


val s1: String = "hello"
val s2: String = null // no no no no no

val os1 = Option(s1)
val os2 = Option(s2)

os1.orNull
os2.orNull


val jl = new java.util.ArrayList[Int]
jl.add(1); jl.add(2); jl.add(3)

jl

// will not compile
//jl.map(_ * 2)
// cuz jl is java type list can't use map
// converting to scala type




jl.asScala.map(_ * 2)
jl.asScala.toList.map(_ * 2)

val sl = List(1, 2, 3)

// Java method signature:
def someJavaFunc(xs: java.util.List[Integer]): java.util.List[Integer] = xs



// Int is not an Integer!
//val r1 = someJavaFunc(sl.asJava)

val jl2 = sl.map( new java.lang.Integer(_) )  // explicitly box first

//jl2.asJava

val r2 = someJavaFunc(jl2.asJava)

val a: Option[String] = Some("hello")

a.isDefined

val b: Option[String] = None

b.isDefined




