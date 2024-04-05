package com.example.kotlin_basics.BankManagementSystem




val banks = hashMapOf<String, ArrayList<ArrayList<User>>>()

abstract class Bank(val bankName: String) {
    fun addUser(account: ArrayList<User>) {
        val bankAccounts = banks[bankName]
        if (bankAccounts == null) {
//            userList.add(account)
            banks[bankName] = arrayListOf(account)
        } else {
            bankAccounts.add(account)
        }
    }

    fun printAllUsers() {
        println("All Users List")
        for (user in banks) {
            println(user)
        }
    }


    abstract fun lowBalanceAccounts(amount: Double)
}


fun main() {
    val branch = Branch("BOB", "Thaltej")
    branch.createAccount(123456789, "Mayur", "Saving", 1111111, 4000.0)
    branch.createAccount(56565, "Rahul", "Saving", 2222222, 3000.0)
    branch.getUserListByBranch()

    val branch1 = Branch("BOB", "Memnagar")
    branch1.createAccount(1523, "Gaurang", "Current", 5555555555, 5000.0)
    branch1.getUserListByBranch()

    val branch2 = Branch("BOB", "Gota")
    branch2.createAccount(1234567890, "Suresh", "Saving", 1010101010101, 6000.0)
    branch2.createAccount(5959559, "Nishit", "Saving", 9876543210, 4500.0)
    branch2.createAccount(123456789, "Suresh", "Saving", 1010101010101, 6000.0)
    branch2.getUserListByBranch()

//    branch.printAllUsers()

    branch.getAccountDetails(56565)

    branch1.withdraw(1523, 800.0)

    branch.deposit(123456789, 1500.0)

    branch2.withdraw(123456789, 2000.0)

    branch2.lowBalanceAccounts(5000.0)

    branch.loanWithInterest(123456789, 10000.0, 5.0)
}