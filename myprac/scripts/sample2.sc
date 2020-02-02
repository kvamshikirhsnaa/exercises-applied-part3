import scala.collection.mutable.ListBuffer

val dict = Map(1 -> "one", 2 -> "two", 3 -> "three")

dict(1)
//dict(0)

dict.get(1)
dict.get(0)

val res: Option[String] = dict.get(1)

val ls = List("Ramu", "had", "a", "little", "lamb")
ls.indexWhere(_.size <= 3)

val l = List("Ramu", "had", "a", "little", "lamb")

l.indexOf("little")


val a = List(1,2,3,4,9,7,12,6,5,7,8,10)

val head = a.head
val headOption = a.headOption
val tail = a.tail
val last = a.last
val removedlast = a.init
val indVal1 = a.init(1)
val indVal2 = a.init(0)
val max = a.max
val indofmax = a.indexOf(a.max)



val maxleft = a.init(a.indexOf(a.max) - 1)

val maxright = a.init(a.indexOf(a.max) + 1)

a.init(5)
a.init(6)
a.dropRight(4)
//a.dropWhile()
//a.init(7)

val lst1 = a.filter(x => a.indexOf(x) < a.indexOf(a.max)).init
val lst2 = a.filter(x => a.indexOf(x) > a.indexOf(a.max)).init

val b = List(40,30,3,4,9,7,4,30,6,12,20,11,13,16)

val maxleft1 = b.drop((b.indexOf(b.max) - 1))

// val maxleft2 = b.init(b.indexOf(b.max) + 1)

def maxNum(ls: List[Int]) = {
  val max = ls.max
  if (max == ls.last) {
    val ls2 = ls.init.init
    val max2 = ls2.max
    max + max2
  }
  else if(max == ls.head) {
    val ls3 = ls.tail.tail
    val max3 = ls3.max
    max + max3
  }
  else {
    val l1 =  ls.filter(x => ls.indexOf(x) < ls.indexOf(ls.max)).init
    val r1 = ls.filter(x => ls.indexOf(x) > ls.indexOf(ls.max)).tail
    val ls2 = l1 ::: r1
    val max4 = ls2.max
    max + max4
  }
}

maxNum(b)

def dropIndex1[T](ls: List[Int]) = {
  val max1 = ls.max
  if (max1 == ls.last) {
    val ls2 = ls.zipWithIndex.filter(x => x._2 != ls.indexOf(ls.max) && x._2 != ls.indexOf(ls.max) - 1).map(x => x._1)
    val max2 = ls2.max
    max1 + max2
  }
  else if (max1 == ls.head) {
    val ls3 = ls.zipWithIndex.filter(x => x._2 != ls.indexOf(ls.max) && x._2 != ls.indexOf(ls.max) + 1).map(x => x._1)
    val max3 = ls3.max
    max1 + max3
  }
  else {
    val ls4 = ls.zipWithIndex.filter(x => x._2 != ls.indexOf(ls.max) - 1 && x._2 != ls.indexOf(ls.max) && x._2 != ls.indexOf(ls.max) + 1).
      map(x => x._1)
    val max4 = ls4.max
    max1 + max4
  }
}

dropIndex1(b)

val donuts: Seq[String] = Seq("Plain Donut 1", "Plain Donut 2", "Strawberry Donut", "Plain Donut 3", "Glazed Donut")
println(s"Elements of donuts = $donuts")

println(s"Drop donut elements whose name starts with letter P = ${donuts.dropWhile(_.charAt(0) == 'P')}")

val dropElementsPredicate: (String) => Boolean = (donutName) => donutName.charAt(0) == 'P'
println(s"Value function dropElementsPredicate = $dropElementsPredicate")

println(s"Drop elements using function from Step 3 = ${donuts.dropWhile(dropElementsPredicate)}")

println(s"Drop elements using function from Step 3 = ${donuts.filter(dropElementsPredicate)}")

println(s"Drop elements using function from Step 3 = ${donuts.filterNot(dropElementsPredicate)}")

val lst = List("four", "three", "four", "two")

lst.dropWhile(x => x.contains("f"))

lst.takeWhile(x => x.contains("f"))
