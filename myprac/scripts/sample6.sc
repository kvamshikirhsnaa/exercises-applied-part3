class Writer(val firstName: String,val surName: String, val year: Int) {
  override def toString: String = s"Writer($firstName, $surName, $year)"

  def fullname: String  = firstName +" "+ surName
}

class Novel(val name: String, val year: Int, val author: Writer) {
  override def toString: String = s"Novel($name, $year, $author)"

  def authorAge() = year - author.year
  def isWrittenBy(author1: Writer): Boolean = author1 == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

val w1 = new Writer("chetan", "bhagat", 1978)
val w2 = new Writer("amish", "tripathi", 1970)
val w3 = new Writer("durjoy", "dutta", 1984)

val n1 = new Novel("half girlfrined", 2016, w1)
val n2 = new Novel("meluha", 2015, w2)
val n3 = new Novel("someone likes you", 2014, w3)

w1.fullname
w2.fullname
w3.fullname

n1.authorAge()
n2.authorAge()
n3.authorAge()

n1.copy(2017)
n2.copy(2016)
n3.copy(2015)

n1.isWrittenBy(w1)
n1.isWrittenBy(w2)
n1.isWrittenBy(w3)

n2.isWrittenBy(w1)
n2.isWrittenBy(w2)
n2.isWrittenBy(w3)

n3.isWrittenBy(w1)
n3.isWrittenBy(w2)
n3.isWrittenBy(w3)









