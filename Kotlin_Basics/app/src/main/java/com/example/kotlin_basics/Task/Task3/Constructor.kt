package com.example.societymaintenancesystem.Task

open class Company(val name: String) {

    init {
        println("Init Company name is $name")
    }

    constructor(name: String, rating: Int) : this(name) {
        println("{Company name is $name & Rating: $rating}")
    }
}

class Employee : Company {



    constructor(
        companyName: String,
        employeeName: String,
        technology: String,
        experience: Double
    ) : super(companyName, 5) {
        println("company name : $companyName\nemployee name: $employeeName\ntechnology : $technology\nexperience : $experience")
    }
    constructor(companyName: String, sdsd:Int, dinfds: Int, sdfknsdf:Int) : super(companyName)

}


fun main() {
    val employee = Employee("Hyperlink", "Mayur", "android", 0.2)
}