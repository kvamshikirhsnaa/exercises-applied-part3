abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
}

class Empty3 extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int) = new NonEmpty3(x, this, this)
  override def toString: String = "."
}

class NonEmpty3(ele: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < ele) left contains x
    else if (x > ele) right contains x
    else true

  def incl(x: Int): IntSet = {
    if (x < ele) new NonEmpty3(ele, left incl x, right)
    else if (x > ele) new NonEmpty3(ele, left, right incl x)
    else this
  }

  override def toString: String = s"{ $left,$ele,$right }"

}

object MyList3 extends App {
  val t1 = new Empty3
  println(t1)
  println(t1.incl(2))
  println(t1.contains(2))
  val t2 = t1 incl 1
  println(t2)


  val n1 = new NonEmpty3(4,t1, t1)
  println(n1)
  println(n1.incl(3))
  println(n1.incl(5))

  val n2 = n1 incl 3
  val n3 = n2 incl 5
  val n4 = n3 incl 6
  val n5 = n4 incl 2
  println(n5)




}