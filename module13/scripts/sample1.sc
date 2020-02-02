val s1 = "vamshikrishna"

s1.toList

s1.toList.map(x => Set(x))


val seq1 = "GTAAGCTTAC"
val seq2 = "GACAGCT-AC"
val seq3 = "G-AACCTAAC"
val seq4 = "C-AACCTAAC"


val listOfSeqs = List(seq1, seq2, seq3, seq4)

def stringToChars(s1: String): List[Char] = s1.toList

stringToChars(seq1)

def stringToSetOfChars(s1: String): List[Set[Char]] = s1.toList.map(x => Set(x))

val aa = stringToSetOfChars(seq1)

val ac = seq2.toList

val res = aa zip ac

val res1, res2 = (aa zip ac)

Set("G") + "C"

for ((a,b) <- (aa zip ac))
  yield a + b

def combineZippedSetsAndString(s1: List[Set[Char]], seq: String): List[Set[Char]] = {
  for ((a, b) <- (s1 zip seq.toList))
    yield a + b
}

val startSet = stringToSetOfChars(seq1)
val nextSet = combineZippedSetsAndString(startSet, seq2)
val finalSet = combineZippedSetsAndString(nextSet, seq3)

finalSet.toString
// should be ("List(Set(G), Set(T, A, -), Set(A, C), Set(A), Set(G, C), Set(C), Set(T), Set(T, -, A), Set(A), Set(C))")

def comboSetsForSequences(sequences: List[String]): List[Set[Char]] = {

  val head :: rest = sequences

  val fstList = stringToSetOfChars(head)
  rest.foldLeft(fstList)(combineZippedSetsAndString(_, _))
}

val allCombos = comboSetsForSequences(listOfSeqs)

// allCombos.toString should be ("List(Set(G, C), Set(T, A, -), Set(A, C), Set(A), Set(G, C), Set(C), Set(T), Set(T, -, A), Set(A), Set(C))")