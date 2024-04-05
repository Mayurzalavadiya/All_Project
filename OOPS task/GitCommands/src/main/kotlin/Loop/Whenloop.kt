package Loop

fun main() {

    var number = 4
    when (number) {
        in 1..5 -> println("One")
        6 -> println("Two")
        7 -> {
            println("welcome")
            println("Three")
        }

        8 -> println("Four")
        10, 12, 13 -> println("Five")
        else -> println("invalid number")
    }

    when  {
        number==4 -> println("One")

        else -> println("invalid number")
    }

}