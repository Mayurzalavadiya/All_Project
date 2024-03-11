package bank

// SBI subclass
class SBI : RBI() {
    // Overriding the loanWithInterest method
    override fun loanWithInterest(loanAmount: Double) {
        super.loanWithInterest(loanAmount)
        val interestPercentage = 8.5
        println(loanAmount + loanAmount * (interestPercentage / 100))
    }

    override fun deposit(amount: Double) {
        balance += amount
        println("SBI: Deposited $amount. Current Balance: $balance")
    }

    fun add(amount: Double){
        deposit(amount)
        println("your amount ${amount} is Successfully Deposite")
    }

    override fun withdraw(amount: Double) {
        if (balance >= amount) {
            balance -= amount
        } else {
            println("Insufficient balance.")
        }
        println("SBI: Withdraw $amount. Current Balance: $balance")
    }
}