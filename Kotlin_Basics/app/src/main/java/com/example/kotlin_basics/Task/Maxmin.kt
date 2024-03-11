package com.example.kotlin_basics.Task


fun repl(array: ArrayList<Int>, first: Int, last: Int) {

    val f = array[first]
    array[first] = array[last]
    array[last] = f

//    for (i in array) {
//        print("$i, ")
//    }


    println("Replace two num $array")


}

fun Duplicate(list: ArrayList<Int>) {

    var dup = ArrayList<Int>()

    for (i in 0 until list.size) {
//        var min = i

        for (j in i until list.size) {
            if (i != j && list[j] == list[i]) {
//                min = j
                dup.add(list[i])
            }
        }
    }
    println("Duplicate $dup")
    list.removeAll(dup)
    println("remove Duplicate $list")
}

fun sortLowtoHigh(list: ArrayList<Int>) {

    for (i in 0 until list.size - 1) {
        var min = i

        for (j in i until list.size) {
            if (list[j] < list[min]) {
                min = j
            }
        }
        val num = list[i]
        list[i] = list[min]
        list[min] = num
    }
    println("Sort $list")
}

fun sortHightoLow(list: ArrayList<Int>) {

    for (i in 0 until list.size - 1) {
        var min = i

        for (j in i until list.size) {
            if (list[j] > list[min]) {
                min = j
            }
        }
        val num = list[i]
        list[i] = list[min]
        list[min] = num
    }
    println("Sort $list")
}

fun reverse(list: ArrayList<Int>) {

    for (i in 0 until list.size - 1) {
        var min = i

        for (j in i until list.size) {
            if ((i + j) == list.size - 1) {
                min = j
            }
        }
        val num = list[i]
        list[i] = list[min]
        list[min] = num
    }
    println("Reverse $list")
}

fun max(list: ArrayList<Int>) {
    var max = list[0]

    for (i in list) {
        if (max < i) {
            max = i
        }
    }

    println("Max num: $max")
}

fun min(list: ArrayList<Int>) {
    var min = list[0]

    for (i in list) {
        if (min > i) {
            min = i
        }
    }

    println("min num: $min")
}


fun main() {

    val array = ArrayList<Int>()
    array.add(3)
    array.add(100)
    array.add(13)
    array.add(13)
    array.add(17)
    array.add(80)
    array.add(80)
    array.add(75)
    array.add(2)
    array.add(1)
    array.add(105)


        print("present list:   $array ")

    println()
    repl(array,2,7)
    reverse(array)
    max(array)
    min(array)
    Duplicate(array)
    sortLowtoHigh(array)
    sortHightoLow(array)


//    for (i in array.size-1 downTo 0){
//        for (j in 0 until array.size)
//        array[i]
//    }

//    var first = array[0]
//
//    for ( i in array){
//        if (first<i){
//            first=i
//        }
//    }
//    println("Max num $first")
//
//
//    for ( i in array){
//        if (first>i){
//            first=i
//        }
//    }
//    print("Min num $first")

}