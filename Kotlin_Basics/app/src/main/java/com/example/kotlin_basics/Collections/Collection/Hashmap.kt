package com.example.kotlin_basics.Collections.Collection

fun main() {
    var hashMap: HashMap<Any, Any> = hashMapOf<Any, Any>()

    hashMap.put("age", 23)
    hashMap.put("name", "Mayur")
    hashMap.put("department", "Android")


    println(hashMap)

    val alphabet = 'A'
    for (i in 0..5) {
        for (j in i..5) {
            if (j != i)
                hashMap.put(j, (alphabet + i))
//            println(hashMap)
        }
    }

    println("$hashMap Add")


    for (key in hashMap.keys) {
        println("keys = $key & values = ${hashMap[key]}")
    }

    println(hashMap.size)

    val n = 4

    when (n) {
        1 -> {
            hashMap.remove(n)
            println("$hashMap remove")
        }

        2 -> {
            hashMap.replace(n, n)
            println("$hashMap replace")
        }

        3 -> {
            println(" ${hashMap.get(n)} get value by key")
        }

        4 -> {
            hashMap.clear()
            println("$hashMap clear")
        }


        5 -> println("${hashMap.containsKey(n)} search")
        else -> println("data not available")


    }

}