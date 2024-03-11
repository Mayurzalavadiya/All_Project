package PatternTask.Alphabet

fun main() {

    val n = 10

    for (i in 1..n) {
        for (j in 1..n) {
            if (i == 1 || i == n || (i + j) - 1 == n)
                print("* ")
            else print("  ")
        }
        println()
    }
}