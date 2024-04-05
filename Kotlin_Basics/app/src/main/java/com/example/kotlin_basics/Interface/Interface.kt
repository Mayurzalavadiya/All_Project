package com.example.kotlinbasics.Interface

interface Test {
    fun salary() {
        println("salary interface test")
    }

}

interface Test1 {

    fun salary() {
        println("salary interface test1")
    }
    fun income()
}

class Employee : Test {

    private lateinit var income: Test1

    fun setincome(test1: Test1) {
        this.income = test1
    }

    override fun salary() {
        super.salary()
        println("salary class")
    }

}


//Multiple Inheritance
class Company: Test,Test1{
    override fun salary() {
        super<Test>.salary()
        super<Test1>.salary()
//        println("sddsf sd f f")
    }

    override fun income() {
        println("Multiple interface company")
    }
}

//  Object
//class C {
//    private fun getObject() = object {
//        val x: String = "x"
//        fun test(){
//            return println("njgdf dfjh fdgb d")
//        }
//    }
//
//    fun printX() {
//        println(getObject().x)
//        getObject().test()
//    }
//}

fun main() {
    val company = Company()
    company.income()
    company.salary()


    val employee = Employee()
    employee.salary()

    val income = object : Test1 {
        override fun income() {
            println("Test1 calling")
        }
    }
    employee.setincome(income)

    income.income()
    income.salary()
//    employee.setincome(object : Test1 {
//        override fun income() {
//            println("income fun")
//        }
//    })
}