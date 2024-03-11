package PatternTask.Alphabet




fun main() {

    val n = 15;

    for (i in 1..n) {
        for (j in 1..n) {
            if (((j == 1) && (i < n)) || ((i == 1) && (j < n)) ||
                ((j == n - 1) && (i < n)) || ((i == n - 1) && (j < n)) ||
                ((j == i) && (i >= n / 2 + 1))
            )
                print("* ")
            else print("  ")
        }
        println()
    }

}