package PatternTask.Diamond


fun main() {

    val n = 5

//////////////////
    for (i in 1..n) {
        for (k in i until n) {
            print(" ")
        }
        for (j in 1..i) {
            if (j == 1 || j == i)
                print("* ")
            else print("  ")
        }


        //Square
        for (k in i until n) {
            print(" ")
        }
        for (j in 1..n) {
            if (j == 1 || i == 1 || j == n)
                print("* ")
            else print("  ")
        }
        println()
    }
    /////////////////////////

    for (i in n - 1 downTo 1) {
        for (k in i until n) {
            print(" ")
        }
        for (j in 1..i) {
            if (j == 1 || j == i)
                print("* ")
            else print("  ")
        }


        //Square
        for (k in i until n) {
            print(" ")
        }
        for (j in 1..n) {
            if (j == 1 || j == n || i == 1)
                print("* ")
            else print("  ")
        }
        println()
    }
////////////////////////////

}