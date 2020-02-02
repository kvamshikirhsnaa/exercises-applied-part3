import scala.collection.mutable

sealed class AccountType
case object Checking extends AccountType
case object Savings extends AccountType

sealed class Transaction(accountType: AccountType, accountNumber: String)

case class OpenAccount(accountType: AccountType, accountNumber: String)
  extends Transaction(accountType, accountNumber)

case class BalanceEnquiry(accountType: AccountType, accountNumber :String)
  extends Transaction(accountType, accountNumber)

case class Deposit(accountType: AccountType, accountNumber: String, amount: BigDecimal)
  extends Transaction(accountType, accountNumber)

case class Withdrawal(accountType: AccountType, accountNumber: String, amount: BigDecimal)
  extends Transaction(accountType, accountNumber)


class Account {
  private var bal: BigDecimal = 0
  def balance = bal
  def deposit(amount: BigDecimal) = bal += amount
  def withdrawal(amount: BigDecimal) = bal -= amount
}

object Account {
  val checkingAccounts = new mutable.HashMap[String, Account]
  val savingsAccounts = new mutable.HashMap[String, Account]

  private def accountsMapForType(accountType: AccountType): collection.mutable.HashMap[String, Account] = {
    accountType match {
      case Checking => checkingAccounts
      case Savings => savingsAccounts
      case _ => throw new IllegalArgumentException("unknown account type")
    }
  }

  def openAccount(accountType: AccountType, number: String) = {
    val account = new Account
    accountsMapForType(accountType) += number -> account
  }

  def getAccount(accountType: AccountType, number: String) = {
    accountsMapForType(accountType)(number)
  }

  def applyTransaction(transaction: Transaction) = {
    transaction match {

      case BalanceEnquiry(accountType, accountNumber) =>
        getAccount(accountType, accountNumber).balance

      case Deposit(accountType, accountNumber, amount) =>
        getAccount(accountType, accountNumber).deposit(amount)

      case Withdrawal(savings, accountNumber, amount)
        if getAccount(savings, accountNumber).balance < amount => throw new IllegalStateException

      case Withdrawal(savings, accountNumber, amount) =>
        getAccount(savings, accountNumber).withdrawal(amount)

      case Withdrawal(checking, accountNumber, amount)
        if (getAccount(checking, accountNumber).balance + 1000) < amount => throw new IllegalStateException

      case Withdrawal(checking, accountNumber, amount) =>
        getAccount(checking, accountNumber).withdrawal(amount)

      case OpenAccount(accountType, accountNumber) =>
        try {
          getAccount(accountType, accountNumber)
          throw new IllegalStateException
        }
        catch {
          case e: NoSuchElementException =>
        }
        openAccount(accountType, accountNumber)

      case _ => throw new IllegalArgumentException("Unknown Transaction Type")
    }
  }
}


  val account = new Account
  println(account.balance)

  account.withdrawal(1000)  // returns unit
  println(account.balance)
  account.withdrawal(2000)  // returns unit
  println(account.balance)

  account.deposit(8000)
  println(account.balance)
  account.deposit(15000)
  println(account.balance)


  //  Account.applyTransaction(BalanceEnquiry(Checking, "001")) // throw Exception cuz we didn't created any acc
  //  Account.applyTransaction(Deposit(Checking, "001", 100))
  //  Account.applyTransaction(Withdrawal(Savings, "002", 100))

  println(Account.applyTransaction(OpenAccount(Checking, "001")))
  println(Account.applyTransaction(BalanceEnquiry(Checking, "001")))

  println(Account.applyTransaction(OpenAccount(Checking, "002")))
  Account.applyTransaction(Deposit(Checking, "002", 100))  // returns unit if we use println

  println(Account.applyTransaction(OpenAccount(Savings, "002")))
  Account.applyTransaction(Deposit(Savings, "002", 200))  // returns unit if we use println

  println(Account.applyTransaction(BalanceEnquiry(Checking, "002")))
  println(Account.applyTransaction(BalanceEnquiry(Savings, "002")))

  println(Account.applyTransaction(OpenAccount(Savings, "003")))
  Account.applyTransaction(Deposit(Savings, "003", 1000))

  Account.applyTransaction(Withdrawal(Savings, "003", 400))
  Account.applyTransaction(Withdrawal(Savings, "003", 400))

  println(Account.applyTransaction(BalanceEnquiry(Savings, "003")))

  // Account.applyTransaction(Withdrawal(Savings, "003", 400)) throws exception

  println(Account.applyTransaction(BalanceEnquiry(Savings, "003")))

  println(Account.applyTransaction(OpenAccount(Checking, "003")))

  Account.applyTransaction(Deposit(Checking, "003", 1000))

  Account.applyTransaction(Withdrawal(Checking, "003", 800))
  Account.applyTransaction(Withdrawal(Checking, "003", 800))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "003")))

  //Account.applyTransaction(Withdrawal(Checking, "003", 800))


  println(Account.applyTransaction(OpenAccount(Checking, "010")))
  println(Account.applyTransaction(OpenAccount(Savings, "020")))

  Account.applyTransaction(Deposit(Checking, "010", 100))
  Account.applyTransaction(Deposit(Savings, "020", 200))

  //  println(Account.applyTransaction(OpenAccount(Checking, "010")))
  //  println(Account.applyTransaction(OpenAccount(Savings, "020")))

  println(Account.applyTransaction(BalanceEnquiry(Checking, "010")))
  println(Account.applyTransaction(BalanceEnquiry(Savings, "020")))


