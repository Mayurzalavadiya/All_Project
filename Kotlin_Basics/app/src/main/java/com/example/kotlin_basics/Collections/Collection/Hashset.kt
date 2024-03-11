package com.example.kotlin_basics.Collections.Collection

fun main() {
    var hashSet: HashSet<Int> = hashSetOf()
    var intSet: HashSet<Int> = hashSetOf()

    hashSet.add(3)
    hashSet.add(5)
    hashSet.add(10)
    hashSet.add(15)
//    hashSet.add("Mayur")

    println(hashSet)

    for (i in 1..10){
        intSet.add(i)
    }
    println(intSet)

    hashSet.addAll(intSet)

    println(hashSet)


    val num = 15

    when(num){
        in 1..3->{
            hashSet.remove(num)
            println("$hashSet remove $num" )
        }
        in 4..8->{
            hashSet.removeAll(intSet)
            println("$hashSet remove $intSet" )
        }

        9,10,15-> {
            println(hashSet.isEmpty())
            println(hashSet.isNotEmpty())
        }

        else -> println("Data not available")
    }

}