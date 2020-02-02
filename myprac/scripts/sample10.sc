// Factory method

trait Computer{
  def getRAM():String
  def getHDD():String
  def getCPU():String

  override def toString =
    "RAM= " + getRAM +", HDD=" + getHDD + ", CPU=" + getCPU
}

object Computer {

  private class PC(val ram: String, val hdd: String, val cpu: String)
    extends Computer {

    def getRAM(): String = ram

    def getHDD(): String = hdd

    def getCPU(): String = cpu

  }

  private class Server(val ram: String, val hdd: String, val cpu: String)
    extends Computer {

    def getRAM(): String = ram

    def getHDD(): String = hdd

    def getCPU(): String = cpu
  }

  def apply(compType: String, ram: String, hdd: String, cpu: String) =
    compType.toUpperCase match {
      case "PC" => new PC(ram, hdd, cpu)
      case "SERVER" => new Server(ram, hdd, cpu)
    }


}

val pc = Computer("pc","2 GB","500 GB","2.4 GHz")
val server = Computer("server","16 GB","1 TB","2.9 GHz")

println("Factory PC Config::"+ pc)
println("Factory Server Config::"+ server)




// using case class we can improve writing factory methods in scala

trait Computer2{
  def ram:String
  def hdd:String
  def cpu:String

  override def toString = "RAM= " + ram +", HDD=" + hdd + ", CPU=" + cpu
}

object Computer2 {

  private case class PC2(ram: String, hdd: String, cpu: String)
    extends Product with Serializable with Computer2

  private case class Server2(ram: String, hdd: String, cpu: String)
    extends Product with Serializable with Computer2


  def apply(compType: String, ram: String, hdd: String, cpu: String)  =
    compType.toUpperCase match {
      case "PC" => PC2(ram, hdd, cpu)
      case "SERVER" => Server2(ram, hdd, cpu)
    }
}

val pc2 = Computer2("pc","2 GB","500 GB","2.4 GHz")
val server2 = Computer2("server","16 GB","1 TB","2.9 GHz")

println("Factory PC Config::"+ pc2)
println("Factory Server Config::"+ server2)



trait A
val a = new A{}

object A1
A1

case object A2
A2

case class A3(name: String)

A3("saikrishna")