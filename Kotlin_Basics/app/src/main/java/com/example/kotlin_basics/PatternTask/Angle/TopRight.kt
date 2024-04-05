package PatternTask.Angle

fun main() {
    val n =5

    for (i in 1..n) {
        for (k in i until  n){
            print("  ")
        }
        for (j in 1..i) {
            if (j == 1 || i == n || j == i)
                print("* ")
            else print("  ")
        }
        println()
    }
}