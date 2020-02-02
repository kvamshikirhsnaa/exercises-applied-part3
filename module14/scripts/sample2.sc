import scala.collection.immutable

val s = List(Set('G', 'C'), Set('T', 'A', '-'), Set('A', 'C'), Set('A'), Set('G', 'C'), Set('C'), Set('T'), Set('T', '-', 'A'), Set('A'), Set('C'))

val s2 = s.zipWithIndex

val s3 = s2.filter(x => x._1.size > 1)

val s4 = s2.filter(x => x._1.size > 1).map(x => x.swap)

s4.toMap

val mutationMap = Map.empty

mutationMap ++ s4


val t = (1,3,5,"seven")

t.productIterator.toList


val a = Set(2,3,5,7,11)

val b = immutable.TreeSet(2,3,4,8,5,6)

b.head
b.tail
b.size
b.last
b.init

