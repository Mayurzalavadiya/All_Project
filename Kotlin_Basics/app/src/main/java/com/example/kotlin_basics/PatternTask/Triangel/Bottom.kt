package PatternTask.Triangel

fun main() {

    val n = 5

    for (i in n downTo 1) {
        for (k in i..n - 1) {
            print(" ")
        }

        for (j in 1..i) {
            if (j == 1 || j == i || i == n)
                print("0 ")
            else
                print("  ")
        }
        println()
    }

}