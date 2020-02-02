import scala.annotation.tailrec

// syntax: foldLeft[B](z: B)(op: (B, A) ⇒ B): B

val input = List(3, 5, 7, 11)

input.reduce((total, cur) => total + cur)

def op(total: Int, cur: Int) = total + cur

input reduce op

List("Foo", "Bar", "Buzz").reduce(_ +" " +_)


def factorial(x: Int) = (2 to x).reduce(_ * _)

factorial(5)

// if list is empty

val z = List.empty

//z.reduce(op) thorws runtime java.lang.UnsupportedException

0 :: z

(0 :: z).reduce(op)
(0 :: Nil).reduce(op)

// if we prepend 0 to emptylist we can use reduce it will work

z.foldLeft(0)(op)


val reverse = (s: String) => s.reverse

val toUpper = (s: String) => s.toUpperCase

val appendBar = (s: String) => s + "bar"

appendBar(toUpper(reverse("foo")))
toUpper(reverse(appendBar("foo")))


def applyTransformations(initial: String, transformations: Seq[String => String]) = {
  val z = transformations.map(x => x(initial))
  z
}

applyTransformations("foo", List(reverse, toUpper, appendBar))
applyTransformations("foo", List(appendBar, reverse, toUpper))
applyTransformations("foo", List.fill(7)(appendBar))


def applyTransformations2(initial: String, transformations: Seq[String => String]) = {
  var cur = initial
  for(transformation <- transformations) {
    cur = transformation(cur)
  }
  cur
}

//Boring loop over all transformations, the intermediate result is stored
//in a variable. This implementation has several drawbacks. First - it's
//imperative (!) Scala tries to embrace the functional programming paradigm
//and this code seems very low-level. Our second take is much more idiomatic
//as far as Scala is concerned - we use recursion and pattern matching:

@tailrec
def applyTransformations3(initial: String, transformations: Seq[String => String]): String = {
  transformations match {
    case head :: tail => applyTransformations3(head(initial), tail)
    case Nil => initial
  }
}

applyTransformations3("foo", List(reverse, toUpper, appendBar))


// reve: List(tou, app) => app3(rev(foo), List(tou,app)
//                      => app3( oof, List(tou, app)
//                      => tou :: List(app) => app3(tou(oof), List(app))
//                                          => app3(OOF, List(app))
//                                             app :: List() => app3(app(OOF), Nil)
//                                                           => app3(OOFbar, Nil)
//                                                           => case Nil => inital = OOFbar

def applyTransformations4(initial: String, transformations: Seq[String => String]) = {
  transformations.foldLeft(initial) {
    (cur, transformation) => transformation(cur)
  }
}

applyTransformations4("foo", List(reverse, toUpper, appendBar))

