package com.example.kotlin_basics.EnumClass

import DAYS

enum class Year(var days: Int) {
    JANUARY(30),
    FEBRUARY(30),
    MARCH(30),
    APRIL(31),
    MAY(30),
    JUNE(31);
}

fun main() {
//        val abc = Year(5)

    val month = Year.APRIL.days
    Year.FEBRUARY.days = 28
    println(Year.FEBRUARY.days)
}