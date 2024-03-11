package com.example.kotlin_basics.EnumClass


enum class Month(var days: Int) {
    JANUARY(31),
    FEBRUARY(30),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
}
// class testing:Month(5){
//
// }
fun main() {
    val month = Month.MARCH
    println("month is $month")
    println(Month.JULY.ordinal)
    println(Month.MAY.days)

    Month.FEBRUARY.days = 28
    println(Month.FEBRUARY.days)

    when (month) {
        Month.JANUARY -> println("Total 31 Days in ${month} month")
        Month.FEBRUARY -> println("Total 29 Days in ${month} month")
        Month.MARCH -> println("Total 31 Days in ${month} month")
        Month.APRIL -> println("Total 30 Days in ${month} month")
        Month.MAY -> println("Total 31 Days in ${month} month")
        Month.JUNE -> println("Total 30 Days in ${month} month")
        Month.JULY -> println("Total 31 Days in ${month} month")
    }
}




