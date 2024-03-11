package PatternTask.Alphabet

fun main() {

    val n = 7

    for (i in 1..n) {
        for (j in 1..n) {
            if (i==j || (i + j) - 1 == n)
                print("* ")
            else print("  ")
        }
        println()
    }
}