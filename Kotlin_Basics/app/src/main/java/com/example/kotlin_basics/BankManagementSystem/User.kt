package com.example.kotlin_basics.BankManagementSystem

data class User(
    val branchName: String,
    val accountNumber: Long,
    val accountHolderName: String,
    val accountType: String,
    val mobileNumber: Long,
    var balance: Double
)