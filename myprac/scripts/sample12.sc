object Cakes {
  abstract class Cake {
    def name: String
    override def toString = s"$name"

  }

  class CupCake extends Cake {
    override def name = "cup cakes"
  }
  class Donut extends Cake {
    override def name = "donut cake"
  }
  class Unknown extends Cake {
    override def name = "unknown but delicious"
  }
}

object CakeFactory {
  import Cakes._

  def apply(cake: String): Cake = cake.toLowerCase match {
    case "cupcake" => new CupCake
    case "donut" => new Donut
    case _ => new Unknown

  }
}

CakeFactory("cupcake")
CakeFactory("donut")
CakeFactory("pastry")


// ----------------------------------------------------------------

object Cakes1 {
  abstract class Cake1 {
    def name: String
    override def toString: String = s"$name"
  }

  class CupCake1 extends Cake1 {
    override def name = "cup cakes"
  }
  class Donut1 extends Cake1 {
    override def name = "donut cake"
  }
  class Unknown1 extends Cake1 {
    override def name = "unknown but delicious"
  }
}

object CakesFactory1 {
  import Cakes1._
  def apply(cake: String): Cake1 = cake.toLowerCase match {
    case "cupcake" => new CupCake1
    case "donut" => new Donut1
    case _ => new Unknown1
  }
  def sample = CakesFactory1
}
CakesFactory1.sample
CakesFactory1("donut")
CakesFactory1("cupcake")
CakesFactory1("pastry")

