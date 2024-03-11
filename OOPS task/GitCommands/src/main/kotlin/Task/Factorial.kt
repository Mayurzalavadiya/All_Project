import java.util.Scanner

// Data class to store user information
data class User(
    val firstname: String,
    val lastname: String,
    val accountNumber: Int,
    val accountType: String,
    var balance: Double
)

// ArrayList to store users
val users = ArrayList<User>()

// Function to add a user using a while-loop
fun addUserWhile() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Flag to control the loop
    var flag = true
// Loop until the user enters "n" or "N"
    while (flag) {
// Prompt the user to enter the details
        println("Enter the firstname:")
        val firstname = scanner.nextLine()
        println("Enter the lastname:")
        val lastname = scanner.nextLine()
        println("Enter the account number:")
        val accountNumber = scanner.nextInt()
        scanner.nextLine() // To consume the newline character
        println("Enter the account type (savings/current):")
        val accountType = scanner.nextLine()
        println("Enter the balance:")
        val balance = scanner.nextDouble()
        scanner.nextLine() // To consume the newline character
// Create a User object and add it to the ArrayList
        val user = User(firstname, lastname, accountNumber, accountType, balance)
        users.add(user)
// Ask the user if they want to continue
        println("Do you want to add another user? (y/n)")
        val choice = scanner.nextLine()
// If the user enters "n" or "N", set the flag to false
        if (choice == "n" || choice == "N") {
            flag = false
        }
    }
}

// Function to add a user using a recursive function
fun addUserRecursive() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Prompt the user to enter the details
    println("Enter the firstname:")
    val firstname = scanner.nextLine()
    println("Enter the lastname:")
    val lastname = scanner.nextLine()
    println("Enter the account number:")
    val accountNumber = scanner.nextInt()
    scanner.nextLine() // To consume the newline character
    println("Enter the account type (savings/current):")
    val accountType = scanner.nextLine()
    println("Enter the balance:")
    val balance = scanner.nextDouble()
    scanner.nextLine() // To consume the newline character
// Create a User object and add it to the ArrayList
    val user = User(firstname, lastname, accountNumber, accountType, balance)
    users.add(user)
// Ask the user if they want to continue
    println("Do you want to add another user? (y/n)")
    val choice = scanner.nextLine()
// If the user enters "y" or "Y", call the function again
    if (choice.equals("y" ,true)) {
        addUserRecursive()
    }
}

// Function to remove a user by their index
fun removeUser() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Prompt the user to enter the index of the user they want to remove
    println("Enter the index of the user you want to remove:")
    val index = scanner.nextInt()
    scanner.nextLine() // To consume the newline character
// Check if the index is valid
    if (index in 0 until users.size) {
// Remove the user from the ArrayList and display a message
        val removedUser = users.removeAt(index)
        println("The user $removedUser has been removed")
    } else {
// Display an error message
        println("Invalid index")
    }
}

// Function to update a user by their index
fun updateUser() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Prompt the user to enter the index of the user they want to update
    println("Enter the index of the user you want to update:")
    val index = scanner.nextInt()
    scanner.nextLine() // To consume the newline character
// Check if the index is valid
    if (index in 0 until users.size) {
// Get the current user from the ArrayList
        val currentUser = users[index]
// Prompt the user to enter new details or keep the old ones
        println("Enter new firstname or press enter to keep ${currentUser.firstname}:")
        val newFirstname = scanner.nextLine()
        println("Enter new lastname or press enter to keep ${currentUser.lastname}:")
        val newLastname = scanner.nextLine()
        println("Enter new account number or press enter to keep ${currentUser.accountNumber}:")
        val newAccountNumber = scanner.nextLine()
        println("Enter new account type or press enter to keep ${currentUser.accountType}:")
        val newAccountType = scanner.nextLine()
        println("Enter new balance or press enter to keep ${currentUser.balance}:")
        val newBalance = scanner.nextLine()
// Create a new User object with the updated details or the old ones if the input is empty
        val updatedUser = User(
            if (newFirstname.isEmpty()) currentUser.firstname else newFirstname,
            if (newLastname.isEmpty()) currentUser.lastname else newLastname,
            if (newAccountNumber.isEmpty()) currentUser.accountNumber else newAccountNumber.toInt(),
            if (newAccountType.isEmpty()) currentUser.accountType else newAccountType,
            if (newBalance.isEmpty()) currentUser.balance else newBalance.toDouble()
        )
// Replace the current user with the updated user in the ArrayList and display a message
        users[index] = updatedUser
        println("The user $currentUser has been updated to $updatedUser")
    } else {
// Display an error message
        println("Invalid index")
    }
}

// Function to deposit money to a user by their account number
fun depositMoney() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Prompt the user to enter the account number of the user they want to deposit money to
    println("Enter the account number of the user you want to deposit money to:")
    val accountNumber = scanner.nextInt()
    scanner.nextLine() // To consume the newline character
// Find the user with the matching account number in the ArrayList
    val user = users.find { it.accountNumber == accountNumber }
// Check if the user is found
    if (user != null) {
// Prompt the user to enter the amount of money they want to deposit
        println("Enter the amount of money you want to deposit:")
        val amount = scanner.nextDouble()
        scanner.nextLine() // To consume the newline character
// Check if the amount is positive
        if (amount > 0) {
// Add the amount to the user's balance and display a message
            user.balance += amount
            println("You have deposited $amount to ${user.firstname} ${user.lastname}'s account. The new balance is ${user.balance}")
        } else {
// Display an error message
            println("Invalid amount")
        }
    } else {
// Display an error message
        println("No user with that account number")
    }
}

// Function to withdraw money from a user by their account number
fun withdrawMoney() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Prompt the user to enter the account number of the user they want to withdraw money from
    println("Enter the account number of the user you want to withdraw money from:")
    val accountNumber = scanner.nextInt()
    scanner.nextLine() // To consume the newline character
// Find the user with the matching account number in the ArrayList
    val user = users.find { it.accountNumber == accountNumber }
// Check if the user is found
    if (user != null) {
// Prompt the user to enter the amount of money they want to withdraw
        println("Enter the amount of money you want to withdraw:")
        val amount = scanner.nextDouble()
        scanner.nextLine() // To consume the newline character
// Check if the amount is positive and less than or equal to the user's balance
        if (amount > 0 && amount <= user.balance) {
// Subtract the amount from the user's balance and display a message
            user.balance -= amount
            println("You have withdrawn $amount from ${user.firstname} ${user.lastname}'s account. The new balance is ${user.balance}")
        } else {
// Display an error message
            println("Invalid amount")
        }
    } else {
// Display an error message
        println("No user with that account number")
    }
}

// Main function
fun main() {
// Scanner object to read input
    val scanner = Scanner(System.`in`)
// Flag to control the loop
    var flag = true
// Loop until the user enters "6" to exit
    while (flag) {
// Prompt the user to choose a function
        println("What do you want to do? (1/2/3/4/5/6)")
        println("1. Add a user using a while-loop")
        println("2. Add a user using a recursive function")
        println("6 - depositMoney")
        println("7 - withdrawMoney")


        val option = scanner.nextInt()
        scanner.nextLine() // To consume the newline character
// Call the appropriate function based on the option
        when (option) {
            1 -> addUserWhile()
            2 -> addUserRecursive()
            3 -> removeUser()
            4 -> updateUser()
            5 -> {
                flag = false // Set the flag to false to exit the loop
                println("Thank you for using the program")
            }

            6 -> depositMoney()
            7 -> withdrawMoney()
            else -> println("Invalid option")
        }
    }}


//package Task
//
//fun main() {
//
//    val num = 5
//    var factorial : Long = 1
//    var i = 1
//
//    while (i <= num) {
//        factorial *= i
//        i++
//    }
//
//    println("$num! == $factorial")
//}
