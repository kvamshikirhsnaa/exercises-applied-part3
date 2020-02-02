abstract class MyList {
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(ele: Int): MyList
  def printElements: String

  override def toString: String = s"[$printElements]"
  }

class Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(ele: Int) = new NonEmpty(ele, this)
  def printElements: String = " "
}

class NonEmpty(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(ele: Int): MyList = new NonEmpty(ele, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}


object ListTest extends App {
  val empty = new Empty
  //empty.head
  //empty.tail
  println(empty)
  println(empty.isEmpty)
  println(empty.add(1))
  println(empty.add(2))
  println(empty.add(3))
  //println(empty.printElements)

  val nonempty = new NonEmpty(10, empty)
  println(nonempty)
  println(nonempty.head)
  println(nonempty.tail)
  println(nonempty.isEmpty)
  println(nonempty.add(20))
  println(nonempty.add(30).add(40).add(20).add(50))

  val nonempty2 = new NonEmpty(10, new NonEmpty(20, new NonEmpty(30, new NonEmpty(40, empty))))
  println(nonempty2)
  println(nonempty2.head)
  println(nonempty2.tail)
}

