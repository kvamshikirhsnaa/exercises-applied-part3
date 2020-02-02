class Person(val name: String, val age: Int) {
  override def toString: String = s"Person($name,$age)"

  def greet1(name: String) =
    println(s"$name says: Hello, $name")

  def greet2(name: String) =
    println(s"$this.name says: Hello, $name")

  def greet3(name: String) =
    println(s"${this.name} says: Hello, $name")

  def greet() =
    println(s"hi my name is $name")

  //multiple constructors
  def this(name: String) = this(name, 25)
  def this() = this("VK")
  def this(age: Int) = this(null)
}

val p1 = new Person("saikrishna", 24)
val p2 = new Person("Aravind")
val p3 = new Person(25)


p1.greet1("prakash")
p1.greet2("prakash")
p1.greet3("prakash")

p1.greet()

p1.name
p1.age

p2.name
p2.age

p3.name
p3.age

