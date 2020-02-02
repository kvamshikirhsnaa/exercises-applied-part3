val words = List("four", "four", "char", "word")
val nums = List(2,3,5,8,13,21)

val sumNums = nums.foldLeft(0)((a, b) => a + b)
val prodNums = nums.foldLeft(1)(_ * _)

val asString = words.foldLeft("")(_ + ", " + _)

val sum2 = nums.reduceLeft(_ + _)

List.empty[Int].foldLeft(0)(_ + _)

// can also use

nums.sum
nums.product

words.toString
words.mkString
words.mkString(",")
words.mkString("[", ",", "]")


// beware reduce on empty Lists
// List.empty[Int].reduceLeft(_ + _)

val words2 = List("one", "two", "three", "four")

val num2: List[Int] = List.empty

val sumNums2 = num2.foldLeft(0)((a,b) => a + b)

val prodNums2 = num2.foldLeft(1)(_ * _)

val asString2 = words2.foldLeft("")(_ + _)

val asString3 = words2.foldLeft("")(_ +" "+ _)

val asString4 = num2.foldLeft("")(_ + _)

val asString5 = nums.foldLeft("")(_ + _)

List.empty[Int].foldLeft(0)((a,b) => a + b)

val sum3 = num2.reduceLeft(_ + _)








