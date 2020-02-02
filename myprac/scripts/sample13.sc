abstract class Animal {
  val creatureType: String

  def eat: String
  override def toString = s"i am $creatureType and i eat $eat"
}

class Dog extends Animal {
  override val creatureType: String = "Domestic"
  def eat = "aamaamaam"
}

trait Carnivore {
  def eat(animal: Animal): Unit
}

class Crocodile extends Animal with Carnivore {
  val creatureType = "wild"
  def eat = "auauauauau"
  def eat(animal: Animal): Unit =
    println(s"I am $creatureType and i am eating ${animal.creatureType}")
  def sample: Animal = new Dog

}

val dog = new Dog

dog.creatureType
dog.eat

val croc = new Crocodile

croc.creatureType
croc.eat
croc.eat(dog)

croc.sample


abstract class Vehicle {
  def name: String
  override def toString: String = s"$name"
}

val vehicle = new Vehicle {
  override def name = "jeep"
}

vehicle.name
