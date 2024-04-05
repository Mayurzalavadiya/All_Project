package com.example.societymaintenancesystem.Task


interface Multiplication {

    fun multiplyNumber(x: Int)
}


interface Additional {

    fun addNumber(X: Int, y: Int, addingNumber: (Int, Int) -> Int)
}


class Calculator : Multiplication, Additional {


    override fun multiplyNumber(x: Int) {
        println(x * x)

    }

    override fun addNumber(x: Int, y: Int, addingNumber: (Int, Int) -> Int) {
        val add = addingNumber.invoke(x, y)
        println(add)
    }
}

fun main() {
    val calculator = Calculator()

    calculator.multiplyNumber(4)

    calculator.addNumber(5, 6, addingNumber)

}

val addingNumber = { x: Int, y: Int -> x + y }