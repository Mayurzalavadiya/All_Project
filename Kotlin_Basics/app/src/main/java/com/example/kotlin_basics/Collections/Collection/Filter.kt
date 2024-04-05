package com.example.kotlin_basics.Collections.Collection


class car {
    val company = "Maruti"
    val baleno = mutableListOf<Baleno>(Baleno(), Baleno(), Baleno())
}

class Baleno {
    val color = "red"
}

data class Team(val color: String, val members: List<String>)

fun main() {

    var listOfLists = mutableListOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9
    )

    val teamA = Team("maruti", mutableListOf( "red"))
    val teamB = Team("maruti", mutableListOf("blue"))
    val teamC = Team("maruti", mutableListOf("red"))
    val teamList = listOf(teamA, teamB, teamC)

    println(teamList)


    val flatmap = teamList.flatMap { it->it.members.map { x -> x}} as ArrayList<*>

    println(flatmap)
    println(listOfLists)

    listOfLists = listOfLists.map { it -> it + 2 }.toMutableList()
    println(listOfLists)

    val array: ArrayList<Int> = arrayListOf(1, 5, 3, 56, 10, 59, 23)

    val filter: ArrayList<Int> = array.filter { it > 10 } as ArrayList<Int>
    println("filter : $filter")

    val numbersMap = mapOf("key1" to 51, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = numbersMap.filter { (key, value) -> key.startsWith("k") && value > 10 }
    println(filteredMap)
}




