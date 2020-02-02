val ls = (1 to 20).toList

// syntax: foldLeft[B](z: B)(op: (B, A) ⇒ B): B

// finidng lenght of any List

2.5 * 6.0 + 6.0
(2.5 * 6.0 + 6.0)/ 7.0

def len(list: List[Any]): Int = list.foldLeft(0) { (count, _) => count + 1 }

len(ls)

// Write the foldLeft equivalent of List.last

def last[A](list: List[A]): A = {
  list.foldLeft[A](list.head){ (_,l) => l}
}

last(ls)

val names = List("saikrishna", "aravind", "prakash", "tilak", "vamshikrishna", "narahari")

last(names)


// Calculate the average of values from a given List[Double].

// val head :: tail = List.empty  throws error

val lst = List(1.0,2.0,3.0,4.0,5.0,6.0)



def average(list: List[Double]): Double = list match {
  case head :: tail => tail.foldLeft(list.head, 1.0) {
    (avg, cur) => ((avg._1 * avg._2 + cur) / (avg._2 + 1.0), avg._2 + 1.0)
  }._1
  case Nil => 1.0
}

average(lst)

// 1.0 :: List(2.0, 3.0, 6.0) => (head, 1.0) {
//          ((1.0, 1.0), 2.0) => ((1.0 * 1.0 + 2.0) / (2.0 + 1.0), 2.0 + 1.0)
//                            => ((3.0/3.0),3.0)
//                            => (1.0, 3.0)
//          ((1.0, 3.0), 3.0) => ((1.0 * 3.0 + 3.0)/ 3.0 + 1.0, 3.0 + 1.0)
//                            => (6.0/4.0, 4.0)
//                            => (1.5, 4.0)
//          ((1.5, 4.0), 4.0) => (1.5 * 4.0 + 4.0/4.0 + 1.0, 4.0 + 1.0)
//                            => (10.0/5.0, 5.0)
//                            => (2.0, 5.0)
//          ((2.0, 5.0, 5.0)  => (2.0 * 5.0 + 5.0/ 5.0 + 1.0, 5.0 + 1.0)
//                            => (15.0/6.0, 6.0)
//                            => (2.5, 6.0)
//          ((2.5, 6.0), 6.0) => (2.5 * 6.0 + 6.0/ 6.0 + 1.0, 6.0 + 1.0)
//                            => (21.0/ 7.0, 7.0)
//                            => (3.0, 7.0)




// Get the value given an integer index of a list and throw an error if out of bound.

def get[A](list: List[A], idx: Int): A = {
  list.tail.foldLeft((list.head, 0)) {
    (r, cur) => if (r._2 == idx) r else (cur , r._2 + 1)
  } match {
    case (result, index) if (idx == index) => result
    case _ => throw new Exception("Bad index!")
  }
}

get(ls, 4)
get(ls, 7)




//class Foo(val name: String, val age: Int, val sex: Symbol)
class Foo(val name: String, val age: Int, val sex: String)

object Foo {
  def apply(name: String, age: Int, sex: String) = new Foo(name, age, sex)
}


val fooList = Foo("Hugh Jass", 25, "male") ::
  Foo("Biggus Dickus", 43, "male") ::
  Foo("Incontinentia Buttocks", 37, "female") ::
  Nil

val stringList = fooList.foldLeft(List[String]()) { (z, f) =>
  val title = f.sex match {
    case "male" => "Mr."
    case "female" => "Ms."
  }
  z :+ s"$title ${f.name}, ${f.age}"
}

stringList(0)
stringList(1)
stringList(2)


