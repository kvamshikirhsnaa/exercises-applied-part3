import java.util.Date

class User () {
  var id: Option[java.lang.Long] = _
  var name: Option[String] = _
  var date: Option[java.util.Date] = _

  def this(id: java.lang.Long,name: String){
    this()
    this.id = Option(id)
    this.name = Option(name)
    this.date = None
  }
}

var obj = new User
obj.id
obj.name
obj.date

var obj1 = new User(1234l,"user")

obj1.id
obj1.name
obj1.date


case class UserNew(id: Option[Long], name: Option[String], date: Option[Date])

object UserNew {
  def apply(id: Option[Long], name: Option[String]): UserNew = {
    UserNew(id, name, None)
  }
}

UserNew(Some(2365l),Some("saikrishna"))

case class UserNew2(id: Option[Long], name: Option[String], date: Option[Date]) {
  def this(id: Long, name: String) {
    this(Some(id),Some(name), None)
  }
}

val z = new UserNew2(12354l, "saikrishna")

z


val s = Some("abs")

