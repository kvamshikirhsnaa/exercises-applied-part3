import scala.collection.mutable
import scala.collection.immutable

val numWords = Map(2 -> "two", 1 -> "one", 3 -> "three", 4 -> "four", 5 -> "five")

val ls = List(1,2,3,4)

numWords(1)     // don't use this
numWords.get(1) // use this
numWords.getOrElse(6, "?") // or this

//val nums = List(1,2,3,2,5,6)  // throws key not found exception
val nums = List(1,2,3,2)
nums.map(numWords)
nums.map(ls)

for ((num, word) <- numWords) {
  println(s"$num -> $word")
}


val tm = immutable.TreeMap.empty[Int, String] ++  numWords

//tm -= 2
//tm += 6 -> "six"

val mm = mutable.Map.empty[Int, String] ++ numWords

mm -= 2
mm += 2 -> "two"


numWords.keys   // iterable (lazy)
numWords.keySet  // Set  //both res same
numWords.values

numWords.filterKeys(_ % 2 == 0)
numWords.mapValues(_.reverse)

numWords.transform { case (k,v) => s"$v($k)" }

numWords.map(_.swap)

val evens = (for (i <- 1 to 5) yield i -> (i % 2 == 0)).toMap
evens.map(_.swap)


val t2 = (1,"one")
t2.swap

// TreeMap sort the elements, Map doesn't sort



