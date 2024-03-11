package PatternTask.Diamond

fun main() {

    val n = 8

    for (i in 1..n) {
        for (k in i..n) {
            print(" ")
        }

        for (j in 1..i) {
            if (j == 1 || j == i)
                print("* ")
            else print("  ")
        }
        println()
    }

    for (i in n - 1 downTo 1) {
        for (k in i..n) {
            print(" ")
        }

        for (j in 1..i) {
            if (j == 1 || j == i)
                print("* ")
            else print("  ")
        }
        println()
    }

    var a = 'a'

    for (i in 1..n) {
        for (j in i..n) {
            print(" ")
        }
        for (j in 1..i) {
            print("${a + j - 1} ")
        }
        println()
    }
    for (i in n - 1 downTo 1) {
        for (i in i..n) {
            print(" ")
        }
        for (j in 1..i) {
            print("${a + j - 1} ")
        }
        println()
    }
}