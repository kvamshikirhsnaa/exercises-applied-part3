//factory method
// Step 1: Define a wrapper object called Cakes to hold various types of cakes
object Cakes1 {

  // Step 2: Define a base trait to represent a Cake
  trait Cake {
    def name: String
  }

  // Step 3: Define class implementations for the Cake trait namely: Cupcake, Donut and UnknownCake
  class Cupcake extends Cake {
    override def name: String = "Cupcake"
  }

  class Donut extends Cake {
    override def name: String = "Donut"
  }

  class UnknownCake extends Cake {
    override def name: String = "Unknown Cake ... but still delicious!"
  }

}

object CakeFactory {
  import Cakes1._

  // Step 5: Define an apply method which will act as a factory to produce the correct Cake implementation
  def apply(cake: String): Cake = {
    cake match {
      case "cupcake" => new Cupcake
      case "donut" => new Donut
      case _ => new UnknownCake
    }
  }
}


println(s"A cupcake = ${CakeFactory("cupcake")}")
println(s"A donut = ${CakeFactory("donut").name}")
println(s"Unknown cake = ${CakeFactory("coconut tart").name}")