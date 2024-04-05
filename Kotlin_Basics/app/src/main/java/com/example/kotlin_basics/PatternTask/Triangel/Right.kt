fun main() {

    val n = 5

    for (i in 1..n) {
        for (j in 1..i) {

            if (j ==1 || j == i)
            print("* ")
            else print("  ")
        }

        println()
    }
    for (i in n-1 downTo 1) {
        for (j in 1..i) {

            if (j ==1 || j == i)
                print("* ")
            else print("  ")
        }
        println()
    }
}