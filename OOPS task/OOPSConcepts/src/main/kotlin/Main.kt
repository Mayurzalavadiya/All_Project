abstract class RBI {

    abstract fun loanFInalAmmount(total: Int)
}

class SBI {

    var rbi: RBI? = null
    val loanRate = 8;

    fun loan(time: Int, amonut: Int) {
       // val sum = mathsNumber +  scienceNumber
        val laonAMount = amonut * loanRate * time /100 + amonut
        rbi!!.loanFInalAmmount(laonAMount)
    }
}


fun main() {
    println("Hello....")

    val sbi = SBI()
    sbi.rbi = object :RBI(){
        //        override fun loanAmount() {
//
//        }
//
//        override fun addNumber(totalNUmber: Int) {
//            println("Sum of number >> $totalNUmber")
//        }
        override fun loanFInalAmmount(total: Int) {
            println("Sum of number >> $total")
        }

    }
    sbi.loan(1, 100000)
//    SBI().addNumber(10,10)

}
