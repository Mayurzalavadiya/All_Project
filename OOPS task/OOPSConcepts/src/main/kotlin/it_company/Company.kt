package it_company

import javax.lang.model.element.Name

abstract class Company {
    var companyName = ""
    var founderName = ""
    var Experience = 0
    private var Ratings = 0.0
    private var open = ""
    private var close = ""

    fun setRating(rating: Double) {
        this.Ratings = rating
    }

    fun openclose(open: String, close: String) {
        this.open = open
        this.close = close
    }

    abstract fun companyType(companytype: String)

    fun services(service1: String, service2: String, service3: String) {
        println("Provide Services: $service1, $service2, $service3")
    }

    fun services(service1: String, service2: String, service3: String, service4: String, service5: String) {
        println("Provide Services: $service1, $service2, $service3, $service4 $service5")
    }

    open fun department(Departments: String, projects: Int) {
        println("Departments Name: ${Departments} & Total of $projects complate projects in this department")
    }

    fun showdetail() {
        println("Company Info: \nName: ${companyName} Founder: ${founderName} Experience: ${Experience} Rating: ${Ratings}")
        println("Company Timing:  Open ${open} AM to ${close} PM")
    }

}

fun main() {

    var e1 = Employee()
    e1.companyName = "Hyperlink"
    e1.founderName = "Harnil Oza"
    e1.Experience = 11
    e1.setRating(4.9)
    e1.openclose("09:00","10:00")
    e1.showdetail()
    e1.services(
        "App Development",
        "Web Development",
        "Blockchain Development",
        "Game Development",
        "Ecommerce Development"
    )
    e1.companyType("Client Base + Product Base")
    e1.Name = "Mayur"
    e1.gender = "Male"
    e1.id = 20
    e1.setContactno(1234567890)
    e1.personalInfo("Thaltej", "03/10/2000")
    e1.department("App Development", 3)


    var e2 = Employee()

}