
class MySample {
  def getCompare(s: String): String = {
    if (s == "Horse") "Cart"
    else if (s == "salt") "Pepper"
    else if (s == "Fish") "Chips"
    else null
  }

  def isNull(ref: Object) = {
    if (ref == null) true
    else false
  }

  def isNone(ref: Object) = {
    if (ref == None) true
    else false
  }

}

val s = new MySample
val s1 = s.getCompare("Horse")
val s2 = s.getCompare(" ")
val s3 = s.getCompare("ejrus")
val s4 = s.getCompare("Fish")

s.isNull(s1)
s.isNull(s2)
s.isNull(s3)
s.isNull(s4)

val ns1 = Option(s.getCompare("Horse"))
val ns2 = Option(s.getCompare(" "))
val ns3 = Option(s.getCompare("ejrus"))
val ns4 = Option(s.getCompare("Fish"))

s.isNone(ns1)
s.isNone(ns2)
s.isNone(ns3)
s.isNone(ns4)

def oneToTen = {
 val ls = for (i <- 1 to 10) yield i
  ls.toList
}

oneToTen

trait MathFunc {
  def apply(n: Int): Int
}

def doMathFuncOnList(ls: List[Int], fn: MathFunc)= {
  ls.map(x => fn(x))
}

val l = List(2,5,6,3,4)

doMathFuncOnList(l, new MathFunc {
  override def apply(n: Int) = n * 2
})

doMathFuncOnList(l, x => x * 2)

Option("hello")

Option.apply("hello")

Option(" ")

Option.empty

val z: String = null

z == null

class TryOption {
  def notDefined(o1: Option[String]) = {
    !o1.isDefined
  }
}
val tryOption = new TryOption

def removeNull(x: String) = x == null || x.trim.isEmpty

def isNotDefined(it: String) = {
  var something: Option[String] = Option.empty
  it match {
    case a if removeNull(it) => something = None
    case b => something = Some(it)
  }
  tryOption.notDefined(something)
}

isNotDefined("hello")

isNotDefined("")

isNotDefined(null)










