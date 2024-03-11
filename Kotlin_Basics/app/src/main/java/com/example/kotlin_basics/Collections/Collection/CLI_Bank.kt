package com.example.kotlin_basics.Collections.Collection

import androidx.core.text.isDigitsOnly
import java.util.Scanner


data class User(
//    val Id: Int,
    var firstname: String,
    var lastname: String,
    val accountNumber: Long,
    var accountType: String,
    var balance: Double
)


val users = ArrayList<User>()

var id = 0

fun createisId(): Int {
    return id++
}

fun main() {
    val scanner = Scanner(System.`in`)
    var flag = true
    while (flag) {
        println("What do you want ? ")
        println("1. Add a user ")
        println("2. Update a user ")
        println("3. Remove a user ")
        println("4. Deposit Money ")
        println("5. Withdraw Money ")
        println("6. Exit the program")
        val choice = scanner.nextInt()
        when (choice) {
            1 -> addWhile()
            2 -> if (users.size > 0) {
                updateUser()
            } else {
                println("data not available")
            }
            3 -> {
                if (users.size > 0) {
                    removeUser()
                } else {
                    println("data not available")
                }
            }
            4 -> {
                if (users.size > 0) {
                    depositMoney()
                } else {
                    println("data not available")
                }
            }
            5 -> {
                if (users.size > 0) {
                    withdrawMoney()
                } else {
                    println("data not available")
                }
            }
            6 -> {
                flag = false
                println("Thank you ")
            }

            else -> println("Invalid option")
        }

        for (i in 0 until users.size) {
            println(users[i])
            println("*******")
        }
    }
}


fun addWhile() {
    val Id = createisId()
    val scanner = Scanner(System.`in`)
    var bool = true
    while (bool) {
        val firstname = userFirstName(scanner)
        val lastname = userLastName(scanner)
        val accountNumber = userAccountNumber(scanner)
        val accountType = userAccountType(scanner)
        val balance = userBalance(scanner)
        users.add(User(firstname, lastname, accountNumber, accountType, balance))
        println("Create Successfully")
        scanner.nextLine()
        println("add another user? (y/n)")
        val choice = scanner.nextLine()
        if (choice.equals("n", true)) {
            bool = false
        }
    }

}

fun userFirstName(scanner: Scanner): String {
    println("Enter firstname:")
    val firstname = scanner.nextLine()
    return if (firstname.trim().isNotEmpty()) {
        if (firstname.replace(" ", "")
                .all { it in '0'..'9' } || firstname.contains("[0-9]".toRegex())
        ) {
            println("Please Enter Valid data :")
            userFirstName(scanner)
        } else {
            firstname.replace(" ", "")
        }

    } else {
        userFirstName(scanner)
    }

}

fun userLastName(scanner: Scanner): String {
    println("Enter lastname:")
    val lastname = scanner.nextLine()
    return if (lastname.trim().isNotEmpty()) {
        if (lastname.replace(" ", "")
                .all { it in '0'..'9' } || lastname.contains("[0-9]".toRegex())
        ) {
            println("Please Enter Valid data :")
            userLastName(scanner)
        } else {
            lastname.replace(" ", "")
        }

    } else {
        userLastName(scanner)
    }

}

fun userAccountNumber(scanner: Scanner): Long {
    println("Enter account number:")
    val accountNumber = scanner.nextLine()
    return if (accountNumber.equals("") || accountNumber.equals(null)) {
        userAccountNumber(scanner)
    } else if (accountNumber.all { it in '0'..'9' }) {
        accountNumber.toLong()
    } else {
        println("Please enter valid account number")
        userAccountNumber(scanner)
    }
}

fun userAccountType(scanner: Scanner): String {
    println("Select account type (savings/current):")

    println("1. Savings ")
    println("2. Current ")
    return when (scanner.nextLine()) {
        "1" -> {
            "savings"
        }

        "2" -> {
            "current"
        }

        else -> {
            println("Invalid option")
            userAccountType(scanner)
        }

    }

}


fun userBalance(scanner: Scanner): Double {
    println("Enter balance:")
    val balance = scanner.next()
    return if (balance.equals("") || balance.equals(null)) {
        return userBalance(scanner)
    } else if (balance.all { it in '0'..'9' }) {
        balance.toDouble()
    } else {
        println("Please enter valid balance")
        userBalance(scanner)
    }
}


fun updateUser() {
    val scanner = Scanner(System.`in`)
    println("Enter the account number for update user:")
    val accountNumber = scanner.nextLine()
    val user = users.find { it.accountNumber == accountNumber.toLong() }
    if (user != null) {
        println("Available firstname is ${user.firstname}:")
        val newFirstname = userFirstName(scanner)
        user.firstname=newFirstname
        println("Available lastname is ${user.lastname}:")
        val newLastname = userLastName(scanner)
        user.lastname=newLastname
        println("Available account type is ${user.accountType}:")
        val newAccountType = userAccountType(scanner)
        user.accountType=newAccountType
//        val updatedUser = User(
//            if (newFirstname.isEmpty()) user.firstname else newFirstname,
//            if (newLastname.isEmpty()) user.lastname else newLastname,
//            user.accountNumber,
//            if (newAccountType.isEmpty()) user.accountType else newAccountType,
//            user.balance
//        )
//        for (i in 0 until users.size) {
//            if (users[i].accountNumber == updatedUser.accountNumber) {
//                users[i] = updatedUser
//            }
//        }
//        println("The old data: $user")
        println("Update successfully")
    } else {
        println("Invalid index")
    }
}

fun depositMoney() {
    val scanner = Scanner(System.`in`)
    println("Enter the account number for deposit money :")
    val accountNumber = userAccountNumber(scanner)
    scanner.nextLine()
    val user = users.find { it.accountNumber == accountNumber.toLong() }
    if (user != null) {
        println("Enter the amount:")
        val amount = scanner.next()
        scanner.nextLine()
        if (amount.toDouble() > 0) {
            user.balance += amount.toDouble()
//            println("You have deposited $amount to ${user.firstname} ${user.lastname}'s account. The new balance is ${user.balance}")
        } else {
            println("Invalid amount")
            depositMoney()
        }
    } else {
        println("No user available")
    }
}

fun withdrawMoney() {
    val scanner = Scanner(System.`in`)
    println("Enter the account number for withdraw money :")
    val accountNumber = userAccountNumber(scanner)
    scanner.nextLine()
    val user = users.find { it.accountNumber == accountNumber.toLong() }
    if (user != null) {
        println("Enter the amount:")
        val amount = scanner.nextDouble()
        scanner.nextLine()
        if (amount > 0 && amount <= user.balance) {
            user.balance -= amount
        } else {
            println("Invalid amount")
            withdrawMoney()
        }
    } else {
// Display an error message
        println("No user available")
    }
}

fun removeUser() {
    val scanner = Scanner(System.`in`)
    println("Enter the account number for remove user:")
    val accountNumber = scanner.nextLine()
    val user = users.find { it.accountNumber.toLong() == accountNumber.toLong() }
    if (user != null) {
        val removedUser = users.remove(user)
        println("Delete Successfully")
        println("The users are $removedUser")
    } else {
        println("Invalid accountNumber")
    }
}


fun addRecursive() {
    val Id = createisId()
    val scanner = Scanner(System.`in`)
    val firstname = userFirstName(scanner)
    val lastname = userLastName(scanner)
    val accountNumber = userAccountNumber(scanner)
    val accountType = userAccountType(scanner)
    val balance = userBalance(scanner)
//    scanner.nextLine()
    users.add(User(firstname, lastname, accountNumber, accountType, balance))

    println("Create Successfully")
//    for (i in 0 until users.size) {
//        println(users[i])
//        println("*******")
//    }
    println("add another user? (y/n)")
    val choice = scanner.nextLine()
    if (choice.equals("y", true)) {
        addRecursive()
    }

}

//