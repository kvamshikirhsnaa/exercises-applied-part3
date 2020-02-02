case class Person(name: String, age: Int)

val xs = List(Person("Harry", 25), Person("Sally", 23), Person("Fred", 31))

"sally" < "fred"

xs.sortWith((p1, p2) => p1.age < p2.age)
xs.sortBy(_.name)

List(5, 2, 3, 4, 8, 1, 7).sorted

// will not work without an implicit ordering
//xs.sorted

//implicit object PersonOrdering1 extends Ordering[Person] {
//  override def compare(x: Person, y: Person) = {
//    if (x.name == y.name) x.age - y.age
//    else if (x.name > y.name) 1 else -1
//  }
//}


implicit object PersonOrdering2 extends Ordering[Person] {
  override def compare(x: Person, y: Person) = {
    if (x.age > y.age) 1 else -1
  }
}

xs.sorted


val xs2 = List(1,2,3)

xs2.tail.tail.tail

val ys2 = List("four", "five")

ys2.tail.tail

xs2.tail.tail.tail eq ys2.tail.tail

xs2.tail.tail.tail equals  ys2.tail.tail

xs2.tail.tail.tail ==  ys2.tail.tail