sealed class AccountType
case object Checking extends AccountType
case object Savings extends AccountType


def checking(at: AccountType): Boolean = at match {
  case Checking => true
  case Savings => false
}

checking(Checking)
checking(Savings)


val xs = List(1,2,3)

def matchList(l: List[Int]): String = {
  l match {
    case a :: rest => "it's a non empty list"
    case Nil => "empty List"
  }
}

matchList(xs)
matchList(Nil)


val ls = List(1,2,3,4,5,6)

val head1 :: tail1 = ls

val head2 :: head3 :: tail2 = ls




val x = ((1, "one"), (2, "two"))

val y = Map((1, "one"), (2, "two"))

x match {
  case ((a, b), c @ (_, _)) =>
    println(a)
    println(b)
    println(c)
  // what are a, b and c here?
}

//val xs1 = List(1,2,3,4)
//
//xs1 match {
//  case List(a, rest) =>
//    println(s"a = $a, rest = $rest")
//}
// throws runtime matchError


val xs2 = List(1,2,3,4)

xs2 match {
  case a :: rest =>
    println(s"a = $a, rest = $rest")
}

val t = (1, "one")

val (a, b) = t

case class Person(first: String, last: String, age: Int)

val p1 = Person("Fred", "Bloggs", 22)

val Person(first, last, 23) = p1


