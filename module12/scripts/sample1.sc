sealed class AccountType
case object Checking extends AccountType
case object Savings extends AccountType

sealed class Transaction(accType: AccountType, accNo: String)

case class BalanceEnquiry(accType: AccountType, accNo: String)
  extends Transaction(accType: AccountType, accNo: String)

case class OpenAccount(accType: AccountType, accNo: String)
  extends Transaction(accType, accNo)

case class Deposit(accType: AccountType, accNo: String, amount: BigDecimal)
  extends Transaction(accType, accNo)

case class Withdrawal(accType: AccountType, accNo: String, amount: BigDecimal)
  extends Transaction(accType, accNo)

class Account {
  private var bal: BigDecimal = 0
  def balance = bal
  def deposit(amt: BigDecimal) = bal += amt
  def withdrawal(amt: BigDecimal) = bal -= amt
}

object Account {
  val checkingAccounts = new collection.mutable.HashMap[String, Account]
  val savingsAccounts = new collection.mutable.HashMap[String, Account]

  private def accountsMapForType(accType: AccountType) = {
    accType match {
      case Checking => checkingAccounts
      case Savings => savingsAccounts
      case _ => throw new IllegalStateException
    }
  }

  def openAccount(accType: AccountType, number: String) = {
    val acc = new Account
    accountsMapForType(accType) += number -> acc
  }

  def getAccount(accType: AccountType, number: String)= {
    accountsMapForType(accType)(number)
  }

  def applyTransaction(transaction: Transaction) = {
    transaction match {
      case BalanceEnquiry(accType, accNo) =>
        getAccount(accType, accNo).balance

      case Deposit(accType, accNo, amount) =>
        getAccount(accType, accNo).deposit(amount)

      case Withdrawal(Savings, accNo, amount)
        if getAccount(Savings, accNo).balance < amount =>
        throw new IllegalStateException

      case Withdrawal(Savings, accNo, amount) =>
        getAccount(Savings, accNo).withdrawal(amount)

      case Withdrawal(Checking, accNo, amount)
        if (getAccount(Checking, accNo).balance) - 1000 < amount =>
        throw new IllegalArgumentException("insufficient funds")

      case Withdrawal(Checking, accNo, amount) =>
        getAccount(Checking, accNo).withdrawal(amount)

      case OpenAccount(accType, accNo) =>
        try {
          getAccount(accType, accNo)
          throw new IllegalStateException
        }
        catch {
          case e: NoSuchElementException =>
        }
        openAccount(accType, accNo)

      case _ => throw new IllegalArgumentException("invalid trancation type")
    }
  }

}


Account.applyTransaction(OpenAccount(Checking, "101"))
// Account.applyTransaction(OpenAccount(Checking, "101"))
Account.applyTransaction(OpenAccount(Savings, "101"))

Account.openAccount(Checking, "101")
Account.openAccount(Checking, "101")

Account.applyTransaction(Deposit(Checking, "101", 2000))

Account.applyTransaction(Withdrawal(Checking, "101", 1000))

Account.getAccount(Checking, "101")

Account.getAccount(Checking, "102")








