package bank

class HDFC : RBI() {

    override fun loanWithInterest(loanAmount: Double) {
        super.loanWithInterest(loanAmount)
        val interestPercentage = 7.5
        println(loanAmount + loanAmount * (interestPercentage / 100))
    }

    override  fun deposit(amount: Double) {
        balance += amount
        println("HDFC: Deposited $amount. Current Balance: $balance")
    }

    override fun withdraw(amount: Double) {
        if (balance >= amount) {
            balance -= amount
        } else {
            println("Insufficient balance.")
        }
        println("HDFC: Withdrawn $amount. Current Balance: $balance")
    }

}