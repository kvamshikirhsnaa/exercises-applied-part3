class Anagram1(w1: String) {
  val length1 = w1.toLowerCase.groupBy(identity).mapValues(x => x.length)

  def singleMatch(w2: String): Boolean = {
    val length2 = w2.toLowerCase.groupBy(identity).mapValues(x => x.length)
    (w1.toLowerCase != w2.toLowerCase) && (length1 == length2)
  }

  def matches(wlst: Seq[String]): Seq[String] = {
    wlst.filter(singleMatch(_))
  }
}

val a1 = new Anagram1("ant")

a1.matches(Seq("tan","nat","tno","hwk","jrl","ant"))


class Anagram2(w: String) {
  val word = w.toLowerCase
  val w_sorted = w.sorted

  def matchesAll(lst: Seq[String]): Seq[String] = {
    lst.filter(x => (x.toLowerCase != word) && (x.sorted == w.sorted))
  }
}

val a2 = new Anagram2("hello")

a2.matchesAll(Seq("hello","lleho","leohl","holel","olleh","hi","helloo","hey"))



class Person(val name: String, val age: Int, val qual: String, val city: String) {
  require(age > 18, "not eligible to cast vote")
  def this(name: String, age: Int, qual: String) = {
    this(name, age, qual, "kotakonda")
  }

  override def toString: String = s"Person($name,$age,$qual,$city)"
}

val p1 = new Person("saikrishna", 24, "btech")

val p2 = new Person("saikrishna", 24, "btech", "delhi")

// val p3 = new Person("tilak",5,"ukg")


class Student(val name: String, val science: Int, val maths: Int, val english: Int) {
  require(maths > 40, "not qualified")

  def this(name: String, science: Int, maths: Int) = {
    this(name, science, maths, if (science > 95) 90 else 65)
  }
  override def toString: String = s"Student($name,$science,$maths,$english)"

  def total(): Int = science + maths + english
  // require(total > 250, "fail")
}

val s1 = new Student("saikrishna",98, 95)

val s2 = new Student("aravind",90, 95, 95)

val s3 = new Student("prakash",90, 95)

val s4 = new Student("narahari",90, 60)

// val s5 = new Student("mahesh",80, 30, 55)

s1.total
s2.total
s3.total
s4.total











