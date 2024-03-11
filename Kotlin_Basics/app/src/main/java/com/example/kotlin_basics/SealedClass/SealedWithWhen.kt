package com.example.kotlin_basics.SealedClass

fun main() {
    val boy = Boy("Mayur")
    val girl = Girl("Nishita")
    checkGender(boy)
}

fun checkGender(gender: Gender) {
    when (gender) {
        is Boy -> println("Boy name is, ${gender.name}")
        is Girl -> println("Girl Name is, ${gender.name}")
    }
}

sealed class Gender {}


class Boy(val name: String) : Gender() {
}

class Girl(val name: String) : Gender() {


}
