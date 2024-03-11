package com.example.kotlin_basics.Review



open class A(val num1: Int, val num2: Int){

    var additionTwoNumber: Int = 0


}

class B : A {


    constructor(num1: Int, num2: Int) : super(num1, num2) {
    }

}


fun main() {
    val b = B(2, 5)
    b.additionTwoNumber = b.num1 + b.num2
    println("adition >> ${b.additionTwoNumber}")
}