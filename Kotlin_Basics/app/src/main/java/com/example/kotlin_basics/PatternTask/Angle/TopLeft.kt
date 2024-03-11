package PatternTask.Angle

fun main() {

    val n = 15

    for (i in 1..n) {

        for (j in 1..i) {
            if (j == 1 || i == n || j == i)
                print("* ")
            else
                print("  ")
        }

        println()
    }

//    for (i in 1..n){
//        for (j in 1..i){
//            if (j==1 || j==i || i==n)
//            print("* ")
//            else
//                print("0 ")
//        }
//        println()
//    }

//    for (i in 1..n){
//        for (j in 1..i){
//            if ((j+i)%2==0 ) {
//                print("1 ")
//            }
//            else
//                print("0 ")
//        }
//        println()
//    }

}