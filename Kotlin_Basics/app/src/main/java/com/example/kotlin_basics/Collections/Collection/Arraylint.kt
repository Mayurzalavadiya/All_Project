package com.example.kotlin_basics.Collections.Collection

fun main() {

    val arraylist: ArrayList<Int> = ArrayList()

    arraylist.add(1)
    arraylist.add(2)
//    Add
//    for (i in 3..10) {
//        arraylist.add(i)
//    }
//
//    println("$arraylist add")
//
//    println(arraylist.indexOf(5))
//
//    arraylist[2] = 15
//    println("$arraylist replace by index")
//
//
//    for (i in 0 until arraylist.size) {
//        for (j in arraylist.size - 1 downTo 0) {
//            if ((i + j) == arraylist.size - 1 && i < j) {
//                println("${arraylist.get(i)} && ${arraylist.get(j)}")
//            }
//        }
//    }


    val n = 7

    when (n) {
        1 -> {
            arraylist.removeAt(n)
            println("$arraylist  remove at $n")
        }

        in 2..5 -> {
            arraylist.removeAt(n)
            println("$arraylist  remove at $n")
        }

        in 6..9 -> {
            arraylist.add(n, n)
            println("$arraylist  add at $n")
        }

        else ->
            println("value not available")

    }

    duplicate(arraylist)

}

fun duplicate(list: ArrayList<Int>) {

    var dup = ArrayList<Int>()

    for (i in 0 until list.size) {


        for (j in i until list.size) {
            if (i != j && list[j] == list[i]) {
                dup.add(list[i])
            }

        }
    }

    println(dup + "duplication")
    list.addAll(dup)
    println("${list} add value")

}