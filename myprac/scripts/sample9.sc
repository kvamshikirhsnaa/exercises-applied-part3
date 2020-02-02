// Factory method

trait Animal {
  def speak
}

object Animal {

  private class Dog extends Animal {
    override def speak { println("woof") }
  }

  private class Cat extends Animal {
    override def speak { println("meow") }
  }

  // my preferred factory method
  def apply(s: String):Animal = {
    if (s == "dog") return new Dog
    else return new Cat
  }

  // an alternative factory method (use one or the other)
  def getAnimal(s: String):Animal = {
    if (s == "dog") return new Dog
    else return new Cat
  }

}


val dog = Animal("dog")
dog.speak

val cat = Animal("cat")
cat.speak


Animal.getAnimal("dog").speak
Animal.getAnimal("cat").speak

