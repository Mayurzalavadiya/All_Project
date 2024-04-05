package com.example.kotlin_basics.Collections.Collection

import com.google.gson.Gson
import kotlin.collections.arrayListOf as arrayListOf1

class Flatmap(name: String) {
    val name: String = name
    val member = arrayListOf1<Int>(1, 2, 3, 4)
}

class Demo() {


    val teams = ArrayList(listOf(Flatmap("A"), Flatmap("B"), Flatmap("C")))
}

fun main() {


    val mainList = ArrayList(listOf(Demo(), Demo()))


    println(Gson().toJson(mainList))
    val totalmembers = mainList
        .flatMap { demo ->
            demo.teams
        }
        .map { map ->
            if (map.name == "A") {
                map.member.add(5)
            } else if (map.name == "C"){
                map.member.remove(2)
            }
            map
        }
        .map { child -> child.member }
        .filter { child -> child.size > 3 }
        .flatMap { child -> child }
        .map { child -> child+2}
        .filter { it%2==0 }

    println(Gson().toJson(totalmembers))

//    val array = [{1,2,2,23,1},{1,2,32,5,656}]

}