package com.example.societymaintenancesystem.Task


interface multiplication {

    fun multiplynumber(x: Int)
}


interface additional {

    fun addnumber(X: Int, y: Int, addingnumber: (Int, Int) -> Int)
}


class calculator : multiplication, additional {


    override fun multiplynumber(x: Int) {
        println(x * x)

    }

    override fun addnumber(x: Int, y: Int, addingnumber: (Int, Int) -> Int) {
        val add = addingnumber.invoke(x, y)
        println(add)
    }
}

fun main() {
    val calculator = calculator()

    calculator.multiplynumber(4)

    calculator.addnumber(5, 6, addingnumber)

}

val addingnumber = { x: Int, y: Int -> x + y }