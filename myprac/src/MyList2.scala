abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
}

class Empty1 extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty1(x, this, this)
  override def toString: String = "."
}

class NonEmpty1(ele: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < ele) left contains x
    else if (x > ele) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < ele) new NonEmpty1(ele, left incl x, right)
    else if (x > ele) new NonEmpty1(ele,left, right incl x)
    else this

  override def toString = s"{ $left,$ele,$right } "
}


object ListTest1 extends App {
  val t1 = new Empty1
//  println(t1)
//  println(t1.contains(1))
//  println(t1.incl(2))

  val t2 = new NonEmpty1(5, t1, t1)
  println(t2)
  val t3 = t2 incl 3
  println(t3)
  val t4 = t3 incl 4
  println(t4)
  val t5 = t4 incl 7
  println(t5)
  val t6 = t5 incl 8
  println(t6)
  val t7 = t6 incl 1
  println(t7)

  val s1 = new NonEmpty1(5, new NonEmpty1(4, new NonEmpty1(3, t1, t1), new NonEmpty1(2, t1, t1)), new NonEmpty1(6, t1, t1))

  println(s1)


  println(t5.contains(7))
  println(t5.contains(100))


}

//
//[3,4,5,7]
//
//9
//
//9 < 7
//9 > 7
