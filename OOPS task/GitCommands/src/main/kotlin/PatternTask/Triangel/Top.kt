package PatternTask.Triangel

fun main() {
    val x = 6
    for (i in 1..x) {
        for (k in i until x) {
            print(" ")
        }
        for (j in 1..i) {
            if (j == 1 || i == x || j == i)
                print("* ")
            else print("  ")
        }

        println()
    }

//    for (i in 1..x) {
//        for (k in i until x) {
//            print("  ")
//        }
//        for (j in i downTo 1) {
//                print("$j ")
//
//        }
//        for (j in 2..i) {
//
//                print("$j ")
//        }
//
//        println()
//    }
}