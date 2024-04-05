package PatternTask.Angle

fun main() {
    val n = 5
    for (i in n downTo 1){

        for (j in i until n){
            print("  ")
        }

        for (j in 1..i){
            if (j == 1 || i == n || j == i)
                print("* ")
            else
                print("  ")
        }
        println()
    }
}
