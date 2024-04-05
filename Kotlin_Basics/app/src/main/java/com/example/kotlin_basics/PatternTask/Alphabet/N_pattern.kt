package PatternTask.Alphabet

fun main() {

    val n = 5
    val mid = n / 2 + 1

    for (i in 1..n) {
        for (j in 1..n) {
            if (j == 1 || j == n || j == i)
                print("* ")
            else print("  ")
        }
        println()
    }
}