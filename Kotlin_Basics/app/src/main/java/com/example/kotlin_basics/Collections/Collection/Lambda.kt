package com.example.kotlin_basics.Collections.Collection


//syntax
//val lambda : type = {arguments -> body}
fun main() {

    val noParam = {
        println("")
        true
        0.2
    }
    println("Argumernt not pass ${noParam()} \n ")


    val double = { num: Int -> num + num }
    println("Value is double Double: ${double(5)} \n")


    val multiplyTo100: (Int) -> String = { input: Int ->
        val number = input * 100
        number.toString()
    }
    println("multiplu to 100: ${multiplyTo100(5)} \n")


    val addStringAndInt = { strInput: String, intInput: Int -> strInput + intInput }
    println("Addition of String And Int: ${addStringAndInt("3", 5)} \n")
    println("Addition of String And Int: ${addStringAndInt("String", 5)} \n")


    val noReturnType = { getInt: Int ->
        getInt
        println("value is $getInt \n")
    }
    println("return type Unit (return no value): ${noReturnType(5)} \n")


    val grade = { getMark: Int ->
        when (getMark) {
            in 0..30 -> "Grade C/Fail"
            in 31..70 -> "Grade B / Average"
            in 71..95 -> "Grade A / Good"
            in 96..99 -> "Grade A+ / Very Good"
            100 -> "Grade A++ / Excellent"
            else -> "Error"
        }
    }
    println("your Grade is: ${grade(10)} \n")

    val calculateOfGrade = fun(mark: Int): String {
        if (mark < 0 || mark > 100){
            return "Error"
        } else if (mark<40){
            return "Fail"
        } else if (mark<70){
            return "Pass"
        }
        return "Excellent"
    }
    println("your Grade is: ${calculateOfGrade(75)} \n")

//Ask to sir about this fun
//    fun extendString(arg: String, num: Int) : String {
//        val another : String.(Int) -> String = { this + it }
//
//        return arg.another(num)
//    }
//    println(extendString("Mayur",5 \n))
//
    val test = Test(1001)
    val obj = { Test(2002) }
    println("test:  ${test.xyz()}")
    println("lambda Object:   $obj")


    val multilineLambda = {
        val a = 5 + 5
        println("dfdfdgret $a")
        "SDfdsf"
        4
    }

    println(multilineLambda())
}

class Test(x: Int) {
    init {
        println("The numbers is: $x")
    }

    fun xyz() {
        println("abs hshg")
    }
}