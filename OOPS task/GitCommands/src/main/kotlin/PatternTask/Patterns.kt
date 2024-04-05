package PatternTask

fun main() {
    var n = 5

    for (i in 1..n) {
        for (j in 1..i) {
            print(" * ")
        }
        println()
    }

    println()

    for (i in n downTo 1) {
        for (j in 1..i) {
            print(" * ")
        }
        println()
    }


    for (i in 1..n) {
        for (j in 1..i) {
            print("$j")
        }

        for (j in i..n-1) {
            print("  ")
        }

//        for (j in i downTo 1) {
//            print("$j")
//        }
        println()
    }

//    for (i in n downTo 1) {
//        for (j in 1..i) {
//            print("$j ")
//        }
//
//        println()
//    }


    var num = 1

    for (i in 1..n) {
        for (j in 1..i) {
            print(" $num ")
            num++
        }

        println()
    }

    var a = 'a'

    for (i in 1..n) {
        for (j in 1..i) {
            print(" $a ")
        }
        a++
        println()
    }

    for (i in 1..n) {
        for (j in 1..n) {
            print("$a ")
        }
        a++

        println()
    }

    for (i in 1..n) {

        for (k in i..n) {
            print("  ")
        }
        for (j in 1..i) {
            print("${a + j - 1} ")
        }

        println()
    }
    var abc = 'a'
    for (i in 1..n) {
        for (j in 1..i) {
            print(" $abc ")
            abc++
        }
        println()
    }


    val x = 6
    for (i in x downTo 1) {
        for (j in i..x) {
            print(" ")
        }
        for (j in 1..i) {
            print("* ")
        }
        println()
    }


    for (i in 1..n) {
        for (k in i..n - 1) {
            print("")
        }

        for (j in 1..i) {

            print("$i")
        }

        for (k in i..n - 1) {
            print("  ")
        }

        for (j in 1..i) {

            print("$i")
        }


        println()
    }

    for (i in n downTo 1) {
        for (j in n downTo i) {
            print(j)
        }
        println()
    }

    for (i in n downTo 1) {
        for (j in i downTo 1) {
            print(j)
        }
        println()

//    main2()
    }
}

fun main2() {

    val ab = mutableListOf("12", "255", "45464", "675", "4776")

    for (i in ab.indices) {

        println(ab[i])
    }}







