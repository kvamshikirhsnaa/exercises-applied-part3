val numsFromOne = Stream.from(1)




val firstTenNums = numsFromOne.take(10)
firstTenNums.toList

val powersOfTwo: Stream[Int] =
  1 #:: powersOfTwo.map(x => x * 2)

val firstFive = powersOfTwo.take(5)

firstFive.toList

val factorial: Stream[BigInt] = BigInt(1) #:: factorial.zip(Stream.from(2)).
  map { case (a, b) => a * b }

val firstTenFacs = factorial.take(10)

firstTenFacs.toList


val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).
  map { case(x, y) => x + y }

fibs.take(20).toList