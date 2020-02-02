import scala.collection.mutable

sealed class AccountType1
case object Checking1 extends AccountType1
case object Savings1 extends AccountType1

sealed class Transaction1(accountType: AccountType1, accountNum: String)

case class OpenAccount1(accountType: AccountType1, accountNum: String)
  extends Transaction1(accountType, accountNum)

case class BalanceEnquiry1(accountType: AccountType1, accountNum: String)
  extends Transaction1(accountType, accountNum)

case class Deposit1(accountType: AccountType1, accountNum: String, amount: BigDecimal)
  extends Transaction1(accountType, accountNum)

case class Withdrawal1(accountType: AccountType1, accountNum: String, amount: BigDecimal)
  extends Transaction1(accountType, accountNum)

class Account1 {
  private var bal: BigDecimal = 0
  def balance = bal
  def deposit(amount: BigDecimal) = bal += amount
  def withdrawal(amount: BigDecimal) = bal -= amount
  //override def toString: String = s"this is ${Account1.accountsMapForType(Checking1)}"
}

object Account1 {

  val checkingAccounts = new mutable.HashMap[String, Account1]
  val savingAccounts = new mutable.HashMap[String, Account1]

  private def accountsMapForType(accountType: AccountType1) = {
    accountType match {
      case Checking1 => checkingAccounts
      case Savings1 => savingAccounts
      case _ => throw new IllegalArgumentException("Unknow account type")
    }
  }

  def openAccount(accountType: AccountType1, number: String): mutable.HashMap[String, Account1] = {
    val account = new Account1
    accountsMapForType(accountType) += number -> account
  }

  def getAccount(accountType: AccountType1, number: String) = {
    accountsMapForType(accountType)(number)
  }

  def applyTransaction(transaction: Transaction1) = {
    transaction match {
      case BalanceEnquiry1(accountType, accountNum) =>
        getAccount(accountType,accountNum).balance

      case Deposit1(accountType1, accountNum, amount) =>
        getAccount(accountType1, accountNum).deposit(amount)

      case Withdrawal1(Savings1, accountNum, amount)
        if getAccount(Savings1, accountNum).balance < amount =>
        throw new IllegalArgumentException("Insufficient Funds")

      case Withdrawal1(Savings1, accountNum, amount) =>
        getAccount(Savings1, accountNum).withdrawal(amount)

      case Withdrawal1(Checking1, accountNum, amount)
        if (getAccount(Checking1, accountNum).balance) + 1000 < amount =>
        throw new IllegalArgumentException("overdraft")

      case Withdrawal1(Checking1, accountNum, amount) =>
        getAccount(Checking1, accountNum).withdrawal(amount)

      case OpenAccount1(accountType1, accountNum) => try {
        getAccount(accountType1, accountNum)
        throw new IllegalStateException()
      }
      catch {
        case e: NoSuchElementException =>
      }
        openAccount(accountType1, accountNum)

      case _ => "Unknown Transaction Type"

    }
  }
}

object bank2 extends App {

  val ac = new AccountType1

  val ac1 = OpenAccount1(ac, "101")

  val be = BalanceEnquiry1(ac, "101")

  val de = Deposit1(ac, "101", 1000)

  val wd = Withdrawal1(ac, "101", 500)

  val wd2 = Withdrawal1(Checking1, "101", 500)

  println(ac)
  println(ac1)
  println(be)
  println(de)
  println(wd)

  val acc = new Account1
  println(acc)
  println(acc.balance)
  println(acc.deposit(1000))
  println(acc.withdrawal(500))

  println(Account1.applyTransaction(OpenAccount1(Checking1, "003")))

  Account1.applyTransaction(Deposit1(Checking1, "003", 1000))

  Account1.applyTransaction(Withdrawal1(Checking1, "003", 800))
  Account1.applyTransaction(Withdrawal1(Checking1, "003", 800))


  println(Account1.applyTransaction(BalanceEnquiry1(Checking1, "003")))

  // Account1.applyTransaction(Withdrawal1(Checking1, "003", 800)) // throws exception

  println(Account1.applyTransaction(BalanceEnquiry1(Checking1, "003")))


  //Account1.getAccount(Checking1, "101")

  //Account1.applyTransaction(OpenAccount1(Checking1, "001"))

}
