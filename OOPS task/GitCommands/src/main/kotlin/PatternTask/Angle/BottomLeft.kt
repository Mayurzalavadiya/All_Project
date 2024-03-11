package PatternTask.Angle

fun main(){

    val n = 5

    for (i in n downTo 1){

        for (j in 1..i){
            if (j == 1 || i == n || j == i)
            print("* ")
            else
                print("  ")
        }
        println()
    }

//    for (i in 1..n){
//
//        for (j in i..n){
//            if (j == i || j == n || i ==1)
//                print("* ")
//            else
//                print("  ")
//        }
//        println()
//    }




}