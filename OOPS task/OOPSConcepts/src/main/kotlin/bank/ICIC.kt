package bank

class ICIC : RBI() {
    override fun loanWithInterest(loanAmount: Double) {
        val interestPercentage = 9.0
        println(loanAmount + loanAmount * (interestPercentage / 100))
    }

    override  fun deposit(amount: Double) {
        balance += amount
        println("ICIC: Deposited $amount. Current Balance: $balance")
    }

    override fun withdraw(amount: Double) {
        if (balance >= amount) {
            balance -= amount
        } else {
            println("Insufficient balance.")
        }
        println("ICIC: Withdrawn $amount. Current Balance: $balance")

    }
}

