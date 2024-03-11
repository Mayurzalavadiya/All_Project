package com.example.kotlin_basics.Task

class Company {
    val COMPANY_NAME = "Mayur Hyperlink infosystem"

    companion object Comp {
        val COMPANY_NAME = "Hyperlink Mayur"
        val COMPANY_Profile = "ABhd dshjj"

    }
    object Employee {
        val NAME = "Mayur"
    }
    fun aa (){
        COMPANY_Profile
        Employee.NAME
    }


}

//object Employee {
//    val COMPANY_NAME = "Hyperlink Mayur"
//    val NAME = "Mayur"
//
//}

fun main() {

//    val company = Company()
    println(Company.COMPANY_NAME)
//    println(Employee.COMPANY_NAME)
    println(Company.Employee.NAME)



}