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
//    val userList = arrayListOf<User>()
//    fun addUser(account: User) {
//        val bankAccounts = banks[bankName]
//        if (bankAccounts == null) {
////            userList.add(account)
//            banks[bankName] = arrayListOf( account)
//        } else {
//            bankAccounts.add(account)
//            userList.remove(account)
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
//
//
//
//fun lowBalanceAccounts(amount: Double) {
//
//        val user = userList.filter { it -> it.balance < amount }
//        if (user.isNotEmpty()) {
//            println("lowBalance >>> ")
//           user.forEach {
//                println(it)
//            }
//        } else {
//            println("User not found")
//        }
//    }
//}
//
//class Branch(bankName: String, val branchName: String) : Account, Bank(bankName) {
//
//    override fun createAccount(
//        accountNumber: Long,
//        holderName: String,
//        accountType: String,
//        mobileNumber: Long,
//        balance: Double
//    ) {
//        val user = checkAccountNumber(accountNumber)
//        if (user == null) {
//            userList.add(
//                User(
//                    branchName,
//                    accountNumber,
//                    holderName,
//                    accountType,
//                    mobileNumber,
//                    balance
//                )
//            )
//            addUser(User(branchName, accountNumber, holderName, accountType, mobileNumber, balance))
////            addUser(branchUserList)
//        } else {
//            println("Account Already created")
//        }
//
//
//
//}
//
//    fun getUserListByBranch() {
//        println("$branchName User List")
//        userList.forEach { println(it) }
//    }
//    override fun getAccountDetails(accountNumber: Long) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            for (i in user) {
//                println("Branch Name : ${i.branchName}")
//                println("Account Number : ${i.accountNumber}")
//                println("Account Holder : ${i.accountHolderName}")
//                println("Account Type : ${i.accountType}")
//                println("Mobile Number : ${i.mobileNumber}")
//                println("Balance : ${i.balance}")
//            }
//        } else {
//            println("Account not found")
//        }
//    }
//
//    override fun deposit(accountNumber: Long, amount: Double) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            user.forEach {
//                it.balance += amount
//                println("deposit in >>>>  Account Number: ${it.accountNumber}, Account Holder: ${it.accountHolderName}, Balance: ${it.balance}")
//            }
//        } else {
//            println("Account not found")
//
//        }
//    }
//
//    override fun withdraw(accountNumber: Long, amount: Double) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            user.forEach{
//            if (it.balance >= amount) {
//                it.balance -= amount
//                println("withdraw in >>>>  Account Number: ${it.accountNumber}, Account Holder: ${it.accountHolderName}, Balance: ${it.balance}")
//            } else {
//                println("Insufficient Balance")
//            }
//        } }else {
//            println("Account not found")
//        }
//
//    }
//
//
//
//
//
//    fun loanWithInterest(
//        accountNumber: Long, loanAmount: Double, rate: Double
//    ) {
//        val user = checkAccountNumber(accountNumber)
//        if (user != null) {
//            println("Loan section >>>")
//            val totalAmount = (loanAmount + loanAmount * (rate / 100))
////            println("your accountNumber ${user.accountNumber} & $loanAmount loan with $rate interest = $totalAmount")
//        } else {
//            println("Account not found")
//        }
//    }
//
//    fun checkAccountNumber(accountNumber: Long): ArrayList<User>? {
//
//        val accounts =
//            userList.filter { it -> it.accountNumber == accountNumber } as ArrayList
//        if (accounts.isNotEmpty()) {
//            return accounts
//        }
//        return null
//    }
//}
//
//
//fun main() {
//    val branch = Branch("BOB", "Thaltej")
//    branch.createAccount(123456789, "Mayur", "Saving", 1111111, 4000.0)
//    branch.createAccount(56565, "Rahul", "Saving", 2222222, 3000.0)
//
//    val branch1 = Branch("BOB", "Memnagar")
//    branch1.createAccount(1523, "Gaurang", "Current", 5555555555, 5000.0)
//
//    val branch2 = Branch("BOB", "Gota")
//    branch2.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
//
//    val branch3 = Branch("BOB", "Gota")
//    branch3.createAccount(5959559, "Nishit", "Saving", 9876543210, 4500.0)
//    branch3.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
//    branch.getUserListByBranch()
//
//
////    branch.getAccountDetails(123456789)
//    branch1.withdraw(1523, 800.0)
//    branch.printAllUsers()
////    branch1.deposit(1523, 1500.0)
//
////    branch3.lowBalanceAccounts(5000.0)
////    branch.loanWithInterest(5959559, 10000.0, 5.0)
//
////    val lowBalance = banks.flatMap { banks -> banks.value.filter { it -> it.balance < 6000.0 } }
////    println("lowBalance >> $lowBalance")
//
//
////    val filterToBranc = banks.flatMap { banks -> banks.value.filter { it -> it.branchName=="Gota"} }
////    println("Filter user list according to Branch >> \n$filterToBranc")
//}
////data class User(
////    val branchName: String,
////    val accountNumber: Long,
////    val accountHolderName: String,
////    val accountType: String,
////    val mobileNumber: Long,
////    var balance: Double
////)
////
////class Branch(val bankName: String, val branchName: String) {
////    private val users = arrayListOf<User>()
////
////    fun createAccount(
////        accountNumber: Long,
////        holderName: String,
////        accountType: String,
////        mobileNumber: Long,
////        balance: Double
////    ) {
////        if (users.any { it.accountNumber == accountNumber }) {
////            println("Account Already exists")
////        } else {
////            users.add(User(branchName, accountNumber, holderName, accountType, mobileNumber, balance))
//////            println("Account created successfully")
////        }
////    }
////
////    fun getAccountDetails(accountNumber: Long) {
////        val user = users.find { it.accountNumber == accountNumber }
////        if (user != null) {
////            println("Branch Name: $branchName")
////            println("Account Number: ${user.accountNumber}")
////            println("Account Holder: ${user.accountHolderName}")
////            println("Account Type: ${user.accountType}")
////            println("Mobile Number: ${user.mobileNumber}")
////            println("Balance: ${user.balance}")
////        } else {
////            println("Account not found")
////        }
////    }
////
////    fun deposit(accountNumber: Long, amount: Double) {
////        val user = users.find { it.accountNumber == accountNumber }
////        if (user != null) {
////            user.balance += amount
////            println("Deposited $amount into Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
////        } else {
////            println("Account not found")
////        }
////    }
////
////    fun withdraw(accountNumber: Long, amount: Double) {
////        val user = users.find { it.accountNumber == accountNumber }
////        if (user != null) {
////            if (user.balance >= amount) {
////                user.balance -= amount
////                println("Withdrawn $amount from Account Number: ${user.accountNumber}, Account Holder: ${user.accountHolderName}, Balance: ${user.balance}")
////            } else {
////                println("Insufficient Balance")
////            }
////        } else {
////            println("Account not found")
////        }
////    }
////
////    fun printAllUsers() {
////        println("All Users List for Branch $branchName:")
////        users.forEach {
////            println(it)
////        }
////    }
////
////    fun lowBalanceAccounts(minBalance: Double) {
////        val lowBalanceUsers = users.filter { it.balance < minBalance }
////        if (lowBalanceUsers.isNotEmpty()) {
////            println("Low Balance Accounts in Branch $branchName:")
////            lowBalanceUsers.forEach {
////                println(it)
////            }
////        } else {
////            println("No low balance accounts in Branch $branchName")
////        }
////    }
////
////    fun getUserListByBranch() {
////        println("User List for Branch $branchName:")
////        users.forEach {
////            println(it)
////        }
////    }
////}
////
////fun main() {
////    val branch1 = Branch("BOB", "Thaltej")
////    branch1.createAccount(123456789, "Mayur", "Saving", 1111111, 5000.0)
////    branch1.createAccount(56565, "Rahul", "Saving", 2222222, 10000.0)
////
////    val branch2 = Branch("BOB", "Memnagar")
////    branch2.createAccount(1523, "Gaurang", "Current", 5555555555, 5000.0)
////
////    val branch3 = Branch("BOB", "Gota")
////    branch3.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
////    branch3.createAccount(5959559, "Nishit", "Saving", 9876543210, 6000.0)
////
////    branch2.printAllUsers()
////
//////    branch1.getAccountDetails(123456789)
////    branch2.withdraw(56565, 800.0)
//////    branch3.deposit(1234567890, 1500.0)
////
//////    branch1.lowBalanceAccounts(6000.0)
////    branch1.getUserListByBranch()
////}