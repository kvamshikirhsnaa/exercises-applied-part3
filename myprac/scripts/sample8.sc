// Factory method

class Person(val name: String)

object Person {

  //factory method
  def from(mother: String, father: String) = new Person("saikrishna")
  def apply(mother: String, father: String): Person = new Person("gouthami")
}



val Anasuya = new Person("Anasuya")
val Srinivasulu = new Person("srinivasulu")

println(Anasuya == Srinivasulu)

val s = Person.from("Anasuya","Srinivasulu")
val g = Person("Anasuya", "Srinivasulu")

s.name
g.name




