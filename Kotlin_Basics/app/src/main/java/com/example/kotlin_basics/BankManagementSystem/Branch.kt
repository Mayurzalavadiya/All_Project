package com.example.kotlin_basics.BankManagementSystem

class Branch : Account, Bank {
   private var branchName : String
    private val userList = ArrayList<User>()


    constructor(bankName: String, branchName: String) : super(bankName) {
        this.branchName = branchName
    }

    override fun createAccount(
        accountNumber: Long,
        holderName: String,
        accountType: String,
        mobileNumber: Long,
        balance: Double
    ) {
        val user = checkAccountNumber(accountNumber)
        if (user == null) {
            userList.add(
                User(
                    branchName,
                    accountNumber,
                    holderName,
                    accountType,
                    mobileNumber,
                    balance
                )
            )
            addUser(userList)
//            addUser(branchUserList)
        } else {
            println("Account Already created")
        }


    }

    fun getUserListByBranch() {
        println("$branchName Branch User List")
        userList.forEach { println(it) }
    }

    override fun getAccountDetails(accountNumber: Long) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            println("Account Details  >>>")
            for (i in user) {
                println("Branch Name : ${i.branchName}")
                println("Account Number : ${i.accountNumber}")
                println("Account Holder : ${i.accountHolderName}")
                println("Account Type : ${i.accountType}")
                println("Mobile Number : ${i.mobileNumber}")
                println("Balance : ${i.balance}")
            }
        } else {
            println("Account not found")
        }
    }
    override fun lowBalanceAccounts(amount: Double) {

        val user = userList.filter { it -> it.balance < amount }
        if (user.isNotEmpty()) {
            println("lowBalance >>> ")
            user.forEach {
                println(it)
            }
        } else {
            println("User not found")
        }
    }




    override fun deposit(accountNumber: Long, amount: Double) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            user.forEach {
                it.balance += amount
                println("deposit in >>>>  Account Number: ${it.accountNumber}, Account Holder: ${it.accountHolderName}, Balance: ${it.balance}")
            }
        } else {
            println("Account not found")

        }
    }

    override fun withdraw(accountNumber: Long, amount: Double) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            user.forEach {
                if (it.balance >= amount) {
                    it.balance -= amount
                    println("withdraw in >>>>  Account Number: ${it.accountNumber}, Account Holder: ${it.accountHolderName}, Balance: ${it.balance}")
                } else {
                    println("Insufficient Balance")
                }
            }
        } else {
            println("Account not found")
        }

    }


    fun loanWithInterest(
        accountNumber: Long, loanAmount: Double, rate: Double
    ) {
        val user = checkAccountNumber(accountNumber)
        if (user != null) {
            println("Loan section >>>")
            val totalAmount = (loanAmount + loanAmount * (rate / 100))
            for (i in user) {
                println("your accountNumber ${i.accountNumber} & $loanAmount loan with $rate interest = $totalAmount")
            }
        } else {
            println("Account not found")
        }
    }

    fun checkAccountNumber(accountNumber: Long): ArrayList<User>? {

        val accounts =
            userList.filter { it -> it.accountNumber == accountNumber } as ArrayList
        if (accounts.isNotEmpty()) {
            return accounts
        }
        return null
    }
}