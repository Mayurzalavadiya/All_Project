package com.example.kotlin_basics.SealedClass

sealed class SealedWithCompanion {

    companion object {
        val COMPANYNAME = "Hyperlink"
        val RATING = 5.0
    }

    object Employee {
        val NAME = "Mayur"
        val AGE = 24
    }
}

fun main() {
println(SealedWithCompanion.COMPANYNAME)
println(SealedWithCompanion.RATING)

println(SealedWithCompanion.Employee.NAME)
println(SealedWithCompanion.Employee.AGE)
}