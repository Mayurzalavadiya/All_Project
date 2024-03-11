package Task

fun main() {

    val num = 5
    var factorial : Long = 1
    var i = 1

    while (i <= num) {
        factorial *= i
        i++
    }

    println("$num! == $factorial")
}

//package com.example.kotlin_basics.Review
//
//interface Account {
//    fun createAccount(
//        accountNumber: Long,
//        holderName: String,
//        accountType: String,
//        mobileNumber: Long,
//        balance: Double
//    )
//
//    fun getAccountDetails(accountNumber: Long)
//    fun deposit(accountNumber: Long, amount: Double)
//    fun withdraw(accountNumber: Long, amount: Double)
//}
//
//data class User(
//    val branchName: String,
//    val accountNumber: Long,
//    val accountHolderName: String,
//    val accountType: String,
//    val mobileNumber: Long,
//    var balance: Double
//)
//
//val banks = hashMapOf<String, ArrayList<User>>()
//
//abstract class Bank(val bankName: String) {
//
//    fun addUser(account: User) {
//        val bankAccounts = banks[bankName]
//        if (bankAccounts == null) {
//            banks[bankName] = arrayListOf(account)
//        } else {
//            bankAccounts.add(account)
//        }
//    }
//
//    fun printAllUsers() {
//        println("All Users List")
//        for (user in banks) {
//            println(user)
//        }
//    }
//
//    fun lowBalanceAccounts(amount: Double) {
//
//        val user = banks.flatMap { banks -> banks.value.filter { it -> it.balance < amount } }
//        if (user.isNotEmpty()) {
//            println("lowBalance >>> ")
//            for (i in user) {
//                println(i)
//            }
//        } else {
//            println("User not found")
//        }
//    }
//
//    fun getUserListByBranch(branchName: String) {
//        val user = banks.flatMap { it -> it.value.filter { it -> it.branchName == branchName } }
//
//        if (user.isNotEmpty()) {
//            println("$branchName branch users >>>")
//            for (i in user) {
//                println(i)
//            }
//        } else {
//            println("User no found in $branchName")
//        }
//    }
//}
//
//
//class Branch(bankName: String, val branchName: String) : Account, Bank(bankName) {
//    override fun createAccount(
//        accountNumber: Long,
//        holderName: String,
//        accountType: String,
//        mobileNumber: Long,
//        balance: Double
//    ) {
//        val user = checkAccountNumber(accountNumber)
//        if (user == null) {
//            addUser(User(branchName, accountNumber, holderName, accountType, mobileNumber, balance))
//        } else {
//            println("Account Already created")
//        }
//    }
//
//
//    override fun getAccountDetails(accountNumber: Long) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
////            println("Bank Name : ${user.key}")
//            println("Branch Name : ${user.branchName}")
//            println("Account Number : ${user.accountNumber}")
//            println("Account Holder : ${user.accountHolderName}")
//            println("Account Type : ${user.accountType}")
//            println("Mobile Number : ${user.mobileNumber}")
//            println("Balance : ${user.balance}")
//        }
//    }
//
//    override fun deposit(accountNumber: Long, amount: Double) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            user.balance += amount
//            println("deposit in >>>>  Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
//        } else {
//            println("Account not found")
//        }
//    }
//
//    override fun withdraw(accountNumber: Long, amount: Double) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            if (user.balance >= amount) {
//                user.balance -= amount
//                println("withdraw in >>>>  Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
//            } else {
//                println("Insufficient Balance")
//            }
//        } else {
//            println("Account not found")
//        }
//
//    }
//
//    fun loanWithInterest(
//        accountNumber: Long, loanAmount: Double, rate: Double
//    ) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            println("Loan section >>>")
//            val totalAmount = (loanAmount + loanAmount * (rate / 100))
//            println("your accountNumber ${user.accountNumber} & $loanAmount loan with $rate interest = $totalAmount")
//        } else {
//            println("Account not found")
//        }
//    }
//}
//
//fun checkAccountNumber(accountNumber: Long): User? {
//
//    for (user in banks.values) for (j in 0 until user.size) if (user[j].accountNumber == accountNumber) {
//        return user[j]
//    }
//    return null
//}
//
//fun main() {
//    val branch = Branch("BOB", "Thaltej")
//    branch.createAccount(123456789, "Mayur", "Saving", 1111111, 5000.0)
//    branch.createAccount(56565, "Rahul", "Saving", 2222222, 10000.0)
//
//    val branch1 = Branch("BOB", "Memnagar")
//    branch1.createAccount(1523, "Gaurang", "Current", 5555555555, 5000.0)
//
//    val branch2 = Branch("BOB", "Gota")
//    branch2.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
//
//    val branch3 = Branch("BOB", "Gota")
//    branch3.createAccount(5959559, "Nishit", "Saving", 9876543210, 6000.0)
//    branch3.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
//    branch.printAllUsers()
//
//
//    branch.getAccountDetails(123456789)
//    branch2.withdraw(1523, 800.0)
//    branch.deposit(1523, 1500.0)
//
//    branch.lowBalanceAccounts(6000.0)
//    branch.getUserListByBranch("Memnagar")
//    branch.loanWithInterest(5959559, 10000.0, 5.0)
//
////    val lowBalance = banks.flatMap { banks -> banks.value.filter { it -> it.balance < 6000.0 } }
////    println("lowBalance >> $lowBalance")
//
//
////    val filterToBranc = banks.flatMap { banks -> banks.value.filter { it -> it.branchName=="Gota"} }
////    println("Filter user list according to Branch >> \n$filterToBranc")
//}