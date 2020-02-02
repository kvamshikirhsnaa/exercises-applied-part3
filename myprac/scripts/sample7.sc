class Counter(val count: Int = 0) {
  def inc = {
    println("increamenting")
    new Counter(count + 1)
  }

  def dec: Counter = {
    println("decreament")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if(n <= 0) this
    else inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if(n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}

val c = new Counter

c.print

c.inc

c.dec

c.print

c.inc.inc(10)

c.inc(10).inc
c.inc(10).inc

c.inc(10)


object Person {
  val n_eyes = 2
  def can_fly = false
}

class Person

Person.n_eyes
Person.can_fly

val saikrishna = new Person
val aravind = new Person

saikrishna == aravind

val vamshikrishna = Person
val ramu = Person

ramu == vamshikrishna




