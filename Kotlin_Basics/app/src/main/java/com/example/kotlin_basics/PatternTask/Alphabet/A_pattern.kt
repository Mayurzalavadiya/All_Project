package PatternTask.Alphabet

fun main() {

    val n = 9;
    val mid = n / 2 + 1

    for (i in 1..n) {
        for (j in 1..n) {
            if (j == 1 || i == 1 || j == n || i == mid)
                print("* ")
            else print("  ")
        }
        println()
    }


    for (i in 1..n) {
        for (k in i..n) {
            print(" ")
        }
        for (j in 1..i) {
            if (j == 1 || j == i || i == mid)
                print("* ")
            else print("  ")
        }
        println()
    }
}