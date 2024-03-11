package com.example.kotlin_basics.SealedClass

import java.util.Calendar

sealed interface Caculator {
    fun add(x: Int, y: Int)

    fun div(x: Double, y: Double)
}

interface Abc : Caculator {
    fun multi(x: Double, y: Double)
}

sealed class SealedWithInterface : Abc {

    class Test : SealedWithInterface() {
        override fun multi(x: Double, y: Double) {
            println("addition of $x & $y is ${x * y}")
        }

        override fun add(x: Int, y: Int) {
            println("addition of $x & $y is ${x + y}")
        }

        override fun div(x: Double, y: Double) {
            println("division of $x & $y is ${x / y}")
        }
    }

    abstract class Test1 : SealedWithInterface() {
        override fun add(x: Int, y: Int) {
            println("addition of $x & $y is ${x + y}")
        }
    }

    class Test2 : Test1() {
        override fun multi(x: Double, y: Double) {
            println("addition of $x & $y is ${x * y}")
        }

        override fun div(x: Double, y: Double) {
            println("division of $x & $y is ${x / y}")
        }
    }
}

fun main() {

    val sum = SealedWithInterface.Test()
    sum.add(5, 6)
    sum.div(5.0, 6.0)

    val test = SealedWithInterface.Test2()
    test.add(8, 4)
    test.div(8.0, 4.0)
}