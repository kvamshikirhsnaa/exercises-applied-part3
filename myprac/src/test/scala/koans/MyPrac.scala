package koans

import scala.collection.JavaConverters._

class MyPrac {

//  val obtuse = new ObtuseJavaLib
//
//  val c1 = Option(obtuse.getComplement("Salt"))
//  val c2 = Option(obtuse.getComplement("Horse"))
//  val c3 = Option(obtuse.getComplement("dkjehr"))
//
//  obtuse.isNull(c1)
//  obtuse.isNull(c2)
//  obtuse.isNull(c3)
//
//  val l1 = obtuse.oneToTen()
//
//
//  val l2 = obtuse.doMathFuncOnList(l1, new ObtuseJavaLib.MathFunc {
//    override def apply(x: Int): Int = x * x
//  } )
//
//
//  def applyFunc(s: Seq[Int], fn: (Int) => Int): List[Int] = {
//    val al = s.toList.map(new java.lang.Integer(_))
//    val mathFunc = new ObtuseJavaLib.MathFunc {
//      override def apply(x: Int): Int = fn(x)
//    }
//    obtuse.doMathFuncOnList(al.asJava, mathFunc).asScala.toList.map(x => x.toInt)
//  }
//
//  applyFunc(List(1,2,3,4), x => x + 10)
//  applyFunc(List(1,2,3,4), x => x * 2)
//
//
//  obtuse.isNotDefined("Fred")
//  obtuse.isNotDefined("")
//
//  val mathObj = obtuse.makeMathObj(5)
//  mathObj.add(10)
//  mathObj.sub(2)
//  mathObj.mul(5)
//  mathObj.div(5)

}

class TryOption {
  def notDefined(o1: Option[String]): Boolean = !o1.isDefined
}

trait MathNum {
  def add(x: Int): Int
  def sub(x: Int): Int
  def mul(x: Int): Int
  def div(x: Int): Int
}

object MyPrac extends App {

  val obtuse = new ObtuseJavaLib

  val c1 = Option(obtuse.getComplement("Salt"))
  val c2 = Option(obtuse.getComplement("Horse"))
  val c3 = Option(obtuse.getComplement("asbehs"))

  println(c1)
  println(c2)
  println(c3)


  println(obtuse.isNull(c1))
  println(obtuse.isNull(c2))
  println(obtuse.isNull(c3))
  println(obtuse.getComplement("asbehs"))

  val l1 = obtuse.oneToTen()

  println(l1)

  val l2 = obtuse.doMathFuncOnList(l1, new ObtuseJavaLib.MathFunc {
    override def apply(x: Int): Int = x * x
  } )

  println(l2)


  def applyFunc(s: Seq[Int], fn: (Int) => Int): List[Int] = {
    val al = s.toList.map(new java.lang.Integer(_))
    val mathFunc = new ObtuseJavaLib.MathFunc {
      override def apply(x: Int): Int = fn(x)
    }
    obtuse.doMathFuncOnList(al.asJava, mathFunc).asScala.toList.map(x => x.toInt)
  }

  println(applyFunc(List(1,2,3,4), x => x + 10))
  println(applyFunc(List(1,2,3,4), x => x * 2))


  println(obtuse.isNotDefined("Fred"))
  println(obtuse.isNotDefined(""))

  val mathObj = obtuse.makeMathObj(5)
  println(mathObj.add(10))
  println(mathObj.sub(2))
  println(mathObj.mul(5))
  println(mathObj.div(5))


}

