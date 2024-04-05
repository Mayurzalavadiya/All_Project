interface Account {
    fun createAccount(accountNumber: Long, holderName: String, accountType: String, balance: Double)
    fun getAccountDetails(accountNumber: Long)
    fun deposit(accountNumber: Long, amount: Double)
    fun withdraw(accountNumber: Long, amount: Double)
    fun calculateLoanInterest(accountNumber: Long, loanAmount: Double, interestRate: Double): Double

}

data class User(
    var branchName: String,
    val accountNumber: Long,
    val accountHolderName: String,
    var accountType: String,
    var balance: Double
)

val banks = hashMapOf<String, ArrayList<User>>()

abstract class Bank(val bankName: String) {
    val branches = arrayListOf<User>()

    fun addUser(account: User) {
        val bankAccounts = banks[bankName]
        if (bankAccounts == null) {
            banks[bankName] = arrayListOf(account)
        } else {
            bankAccounts.add(account)
        }
    }

    fun printAllUsers() {
        println("All Users List")
        for ((bank, users) in banks) {
            println("$bank=$users")
        }
    }

    fun printLowBalanceAccounts(amount: Double) {
        for (account in branches) {
            if (account.balance < amount) {
                println("Account Number: ${account.accountNumber}, Account Holder: ${account.accountHolderName}, Balance: ${account.balance}")
            }
        }
    }

    fun printUsersByBranch(branchName: String) {
        val usersInBranch = banks.values.flatten().filter { it.branchName == branchName }

        if (usersInBranch.isNotEmpty()) {
            println("Users in Branch '$branchName':")
            usersInBranch.forEach {
                println("Account Number: ${it.accountNumber}, Account Holder: ${it.accountHolderName}, Balance: ${it.balance}")
            }
        } else {
            println("No users found in Branch '$branchName'.")
        }
    }

}

class Branch(bankName: String, val branchName: String) : Account, Bank(bankName) {
    override fun createAccount(
        accountNumber: Long,
        holderName: String,
        accountType: String,
        balance: Double
    ) {
        val user = checkAccountNumber(accountNumber)
        if (user == null) {
            addUser(User(branchName, accountNumber, holderName, accountType, balance))
        } else {
            println("Account Already created")
        }
    }

    override fun calculateLoanInterest(accountNumber: Long, loanAmount: Double, interestRate: Double): Double {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            val interest = loanAmount * (interestRate / 100)
            println("Interest for Account Number ${user.accountNumber}: $interest")
            return interest
        } else {
            println("Account not found")
            return 0.0
        }
    }

    override fun getAccountDetails(accountNumber: Long) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            println("Branch Name : ${user.branchName}")
            println("Account Number : ${user.accountNumber}")
            println("Account Holder : ${user.accountHolderName}")
            println("Account Type : ${user.accountType}")
            println("Balance : ${user.balance}")
        }
    }

    override fun deposit(accountNumber: Long, amount: Double) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            user.balance += amount
            println("Deposit in >>>>  Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
        } else {
            println("Account not found")
        }
    }

    override fun withdraw(accountNumber: Long, amount: Double) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            if (user.balance >= amount) {
                user.balance -= amount
                println("Withdraw in >>>>  Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
            } else {
                println("Insufficient Balance")
            }
        } else {
            println("Account not found")
        }
    }
}

fun checkAccountNumber(accountNumber: Long): User? {
    for (bankAccounts in banks.values) {
        for (user in bankAccounts) {
            if (user.accountNumber == accountNumber) {
                return user
            }
        }
    }
    return null
}

fun main() {
    val branch = Branch("BOB", "Thaltej")
    branch.createAccount(123456789, "Mayur", "Saving", 5000.0)
    branch.createAccount(56565, "Rahul", "Saving", 10000.0)
    val branch1 = Branch("SBI", "Memnagar")
    branch1.createAccount(1523, "Gaurang", "Curent", 5000.0)
    val branch2 = Branch("SBI", "Gota")
    branch2.createAccount(5898, "Gaurang", "Curent", 5000.0)
    branch.printAllUsers()

    branch2.deposit(1523, 500.0)
    branch.withdraw(123456789, 1000.0)

    branch.printUsersByBranch("Thaltej")

    val lowBalance = banks.flatMap { it.value.filter { it.balance < 6000.0 } }
    println("Low Balance Users:")
    for (user in lowBalance) {
        println("Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
    }
}