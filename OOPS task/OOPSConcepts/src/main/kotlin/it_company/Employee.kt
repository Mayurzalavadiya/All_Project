package it_company

class Employee : Company() {
    var Name = ""
    var id = 1
    var gender = ""
    private var contactNo = 123

    fun setContactno(contactno : Int){
        this.contactNo = contactno
    }

    fun personalInfo(Address: String, DOB: String) {
        println("Employee Info: \nName: ${Name} id: ${id} Gender: ${gender} Address: ${Address} DOB: ${DOB} contact No: ${contactNo}")
    }

    override fun department(Departments: String, projects: Int) {
        println("Departments Name: ${Departments} & Complate Projects ${projects}")
    }

    override fun companyType(companytype: String) {
        println("Company Type: ${companytype}")
    }

}