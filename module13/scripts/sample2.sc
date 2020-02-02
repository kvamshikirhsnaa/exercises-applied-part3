val numbers: List[Int] = List(10, 3, 21, 7, 9, 13, 15, 10, 1, 2, 1, 9,8,15,13,10, 12,16)


numbers.splitAt(10)
val ns = numbers.splitAt(numbers.size / 2)

numbers.partition(x => x%3 == 0)

val mid = numbers(numbers.size / 2)

val (l1, r1) = numbers.partition(x => x < mid)

val s: List[Int] = List()

s.size

val (l2, r2) = s.partition(x => x < 2)

def sample(numbers: List[Int]): (Int, List[Int], Int)= {
  val head = numbers.head
  val lower = numbers.tail.init
  val last = numbers.last
  (head, lower, last)
}
val (a,b,c)  = sample(numbers)

def partitionByFirst(numbers: List[Int]): (Int, List[Int], List[Int])= {
  val head :: rest = numbers
  val (lower, remaining) = rest.partition(x => x < head)
  (head, lower, remaining)
}

partitionByFirst(numbers)


val (h, l, r) = partitionByFirst(numbers)

def mysteryFunction(numbers: List[Int]): List[Int] = {
  numbers match {
    case Nil => Nil
    case xs: List[Int] => {
      val head :: rest = xs
      val (lower, remaining) = rest.partition(x => x < head)
      mysteryFunction(lower) ::: List(head) ::: mysteryFunction(remaining)
    }
  }
}

mysteryFunction(numbers)

val numbers2: List[Int] = List(10,2,5,6,8,4,3,9,77,25,55,6,2,9,88,33,66,44,88,6,9,1,2,90)

def mysteryFunc(numbers: List[Int]): List[Int] = {
  numbers match {
    case Nil => Nil
    case num: List[Int] => {
      val mid = num(num.size / 2)
      val ns = num.filter(x => x != num(num.size / 2))
      val (l1, r1) = ns.partition(x => x <= mid)
      mysteryFunc(l1) ::: List(mid) ::: mysteryFunc(r1)

    }
  }
}
mysteryFunc(numbers2)


