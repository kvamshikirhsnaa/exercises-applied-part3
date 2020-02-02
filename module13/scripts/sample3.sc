val numbers: List[Int] = List(1,4,6,7,5,4,5,9,8,3,7,5,9,6,3)

numbers.span(x => x < 9 )
numbers.partition(x => x < 9)

numbers.lift
numbers.lift(2)

numbers.indexOf(numbers.size/2)
numbers.indexOf(numbers.max) - 1

def dropIndex[T](list: List[T], idx: Int): List[T] =
  list.zipWithIndex.filter(_._2 != idx).map(_._1)

dropIndex(numbers, 1)

def mysteryFunc(numbers: List[Int]): List[Int] = {
  numbers match {
    case Nil => Nil
    case num: List[Int] => {
      val mid = num(num.size / 2)
      val ns = dropIndex(num, num.size/2)
      val (l1, r1) = ns.partition(x => x <= mid)
      mysteryFunc(l1) ::: List(mid) ::: mysteryFunc(r1)

    }
  }
}

val res = mysteryFunc(numbers)

numbers.size == res.size



