package com.example.societymaintenancesystem.Task

open class company(val name: String) {

    init {
        println("Init Company name is $name")
    }

    constructor(name: String, rating: Int) : this(name) {
        println("{Company name is $name & Rating: $rating}")
    }
}

class employee : company {

    constructor(companyName: String) : super(companyName)

    constructor(
        companyName: String,
        employeename: String,
        technology: String,
        experience: Double
    ) : super(companyName, 5) {
        println("company name : $companyName\nemployee name: $employeename\ntechnology : $technology\nexperience : $experience")
    }


}


fun main() {
    val employee = employee("Hyperlink", "Mayur", "android", 0.2)
}