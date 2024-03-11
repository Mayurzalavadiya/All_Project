package com.example.kotlin_basics.BankManagementSystem

interface Account {
    fun createAccount(
        accountNumber: Long,
        holderName: String,
        accountType: String,
        mobileNumber: Long,
        balance: Double
    )

    fun getAccountDetails(accountNumber: Long)
    fun deposit(accountNumber: Long, amount: Double)
    fun withdraw(accountNumber: Long, amount: Double)
}
