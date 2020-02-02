import Account.getAccount

import scala.collection.mutable

sealed class AccountType

case object Checking extends AccountType
case object Savings extends AccountType

sealed class Transaction(accType: AccountType, accNo: String)

case class BalanceEnquiry(accType: AccountType, accNo: String)
  extends Transaction(accType: AccountType, accNo: String)

case class OpenAccount(accType: AccountType, accNo: String)
  extends Transaction(accType: AccountType, accNo: String)

case class Deposit(accType: AccountType, accNo: String, amount: BigDecimal)
  extends Transaction(accType: AccountType, accNo: String)

case class Withdrawal(accType: AccountType, accNo: String, amount: BigDecimal)
  extends Transaction(accType: AccountType, accNo: String)

class Account {
  private var bal: BigDecimal = 0
  def balance = bal
  def deposit(amount: BigDecimal) = bal += amount
  def withdrawal(amount: BigDecimal) = bal -= amount
}

object Account {

  val checkingAccounts = new mutable.HashMap[String, Account]
  val savingAccounts = new mutable.HashMap[String, Account]

  private def accountsMapForType(accType: AccountType) = {
    accType match {
      case Checking => checkingAccounts
      case Savings => savingAccounts
      case _ => throw new IllegalStateException
    }
  }

  def openAccount(accType: AccountType, number: String) = {
    val acc = new Account
    accountsMapForType(accType) += number -> acc
  }

  def getAccount(accType: AccountType, number: String) = {
    accountsMapForType(accType)(number)
  }

  def applyTransaction(transaction: Transaction) = {
    transaction match {
      case BalanceEnquiry(accType, accNo) =>
        getAccount(accType, accNo).balance

      case Deposit(accType, accNo, amount) =>
        getAccount(accType, accNo).deposit(amount)

      case Withdrawal(Savings, accNo, amount)
        if  getAccount(Savings, accNo).balance < amount =>
        throw new IllegalArgumentException("insuffiencient funds")

      case Withdrawal(Savings, accNo, amount) =>
        getAccount(Savings, accNo).withdrawal(amount)

      case Withdrawal(Checking, accNo, amount)
        if (getAccount(Checking, accNo).balance) - 1000 < amount =>
        throw new IllegalArgumentException("overdraft")

      case Withdrawal(Checking, accNo, amount) =>
        getAccount(Checking, accNo).withdrawal(amount)

      case OpenAccount(accType, accNo) => try {
        getAccount(accType, accNo)
        throw new IllegalStateException()
      }
        catch {
          case e: NoSuchElementException =>
        }
        openAccount(accType, accNo)


      case _ => throw new IllegalArgumentException("invalid transaction")

    }
  }



}



object bank3 extends App{

  val acc = new Account
  println(acc)
  println(acc.balance)
  acc.deposit(1000)
  println(acc.balance)
  acc.withdrawal(500)
  println(acc.balance)
  acc.withdrawal(1000)
  println(acc.balance)

  val ac = new AccountType

  println(ac)

  val t1 = new Transaction(ac, "101")
  val t2 = new Transaction(Checking, "101")
  val t3 = new Transaction(Savings, "101")

  println(t1)
  println(t2)
  println(t3)

  val be = BalanceEnquiry(Checking, "104")

  println(be)

  val be2 = BalanceEnquiry(ac, "104")

  println(be2)

  println(Account.openAccount(Checking, "101"))
  println(Account.openAccount(Checking, "101"))
  println(Account.openAccount(Savings, "101"))
  println(Account.openAccount(Checking, "102"))
  println(Account.openAccount(Savings, "102"))

  println(Account.applyTransaction(OpenAccount(Checking, "103")))

  // println(Account.applyTransaction(OpenAccount(Checking, "103")))

  // println(Account.applyTransaction(BalanceEnquiry(ac, "101")))

  println(Account.applyTransaction(BalanceEnquiry(Savings, "101")))

  Account.applyTransaction(Deposit(Savings, "101", 10000))

  println(Account.applyTransaction(BalanceEnquiry(Savings, "101")))

  Account.applyTransaction(Withdrawal(Savings, "101", 5000))

  println(Account.applyTransaction(BalanceEnquiry(Savings, "101")))

 // Account.applyTransaction(Withdrawal(Savings, "101", 6000))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "101")))

  // Account.applyTransaction(Withdrawal(Checking, "101", 1000))

  // println(Account.applyTransaction(BalanceEnquiry(Checking, "101")))

  Account.applyTransaction(Deposit(Checking, "101", 2000))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "101")))

  Account.applyTransaction(Withdrawal(Checking, "101", 1000))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "101")))

 // println(Account.applyTransaction(BalanceEnquiry(Checking, "104")))

  println(Account.applyTransaction(OpenAccount(Checking, "105")))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "105")))




























}
