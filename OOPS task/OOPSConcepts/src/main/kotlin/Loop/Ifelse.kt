package Loop

fun main() {
    val x = 10
    val y = 20

    if (x > y) {
        println("x is greater than y")
    }

    val day = 2

    val result = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day."
    }
    println(result)


    var i = 1
    while (i<=5){
        println(i)
        i++
    }

    println("\n")

    var j = 6
    do {
        println(j)
        j++
    }
    while (j<=5);
}