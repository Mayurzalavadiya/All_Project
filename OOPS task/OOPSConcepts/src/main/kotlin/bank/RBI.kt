package bank

class AccType {
    companion object {
        internal const val SAVINGS = "Savings";
        internal const val CURRENT = "Current";
        internal const val FIX_ACCOUNT = "Fixed Deposit";
    }
}
abstract class RBI {

    // Properties
    var accNo: String = ""
    var accName: String = ""
    private var accType: String = AccType.CURRENT
    var balance: Double = 0.0

    fun accDetail() {
        println("accNo: ${accNo} accName: ${accName} acctype: ${accType}")
    }

    internal fun changeAccType(accType : String){
        println("acctype change request")
        this.accType = accType
        println("accType change successfully")
        accDetail()
    }

    open fun loanWithInterest(loanAmount: Double) {
        val loanPercentage = 6.0
        println(loanAmount + loanAmount * (loanPercentage / 100))
    }

    abstract  fun deposit(amount: Double)

    abstract fun withdraw(amount: Double)
}


fun main() {
    val sbi = SBI()
    sbi.accNo = "SBIN000123"
    sbi.accName = "Rahul shah"
    sbi.accDetail();

    sbi.changeAccType(AccType.SAVINGS)



    val sbiLoanAmount = 100000.0
    sbi.loanWithInterest(sbiLoanAmount)
//    val sbiLoanWithInterest = sbi.loanWithInterest(sbiLoanAmount)
//    println("SBI Loan with interest: $sbiLoanWithInterest")

    sbi.add(20000.0)
    sbi.withdraw(15000.0)

    val icic = ICIC()
    icic.accNo = "ICIC000456"
    icic.accName = "Raj Zala"

    icic.accDetail();

    val icicLoanAmount = 150000.0
    icic.loanWithInterest(icicLoanAmount)

//    val icicLoanWithInterest = icic.loanWithInterest(icicLoanAmount)
//    println("ICIC Loan with interest: $icicLoanWithInterest")

    val hdfc = HDFC()
    hdfc.accNo = "HDFC000789"
    hdfc.accName = "Darshan Pandya"

    hdfc.accDetail();

    val hdfcLoanAmount = 200000.0
    hdfc.loanWithInterest(hdfcLoanAmount)

//    val hdfcLoanWithInterest = hdfc.loanWithInterest(hdfcLoanAmount)
//    println("HDFC Loan with interest: $hdfcLoanWithInterest")

    hdfc.deposit(20000.0)
    hdfc.withdraw(20001.0)

}


//public: This is the default access modifier, meaning if you don't specify any access modifier explicitly, the element will be public. Public elements can be accessed from anywhere in the same module or from other modules.
//private: Elements marked as private are only accessible within the same class or file where they are declared. They cannot be accessed from outside the class or file. This is useful when you want to hide internal implementation details.
//protected: Elements marked as protected are visible within the same class and its subclasses. They cannot be accessed from outside the class hierarchy. This is often used when you want to provide access to certain elements for subclasses but keep them hidden from other classes.
//internal: Elements marked as internal are visible within the same module. A module is a set of Kotlin files that are compiled together. This access modifier is useful when you want to share elements between files within the same module but keep them hidden from other modules.