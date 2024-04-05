package buildingManagement.builder

abstract class Builder {
    var groupName = ""
    var totalProjects = 1
    var projectName = ""
    var experience = 10

    fun projectDetail(workingProjects: Int, newProjects: Int) {
        println(
            "Projects Details: \nWorking Projects: ${workingProjects} " +
                    "New Projects: ${newProjects} "
        )
    }

    abstract fun upcomingProjects(number: Int)

    open fun showDetails() {
        println("Builder Info \nGroup Name: $groupName, Project Name: $projectName, Total Projects: $totalProjects, ")
    }
}

fun main() {
    val shivalika = Shivalika()
    shivalika.groupName = "Shivalik Group"
    shivalika.totalProjects = 10
    shivalika.projectName = "Shivalik Shilp"
    shivalika.setName("Mayur")
    shivalika.setAddress("D-417 Ganesh Gloory")
    shivalika.setAge(23)
    shivalika.showDetails()
//    shivalika.PersonalInfo("Mayur","D-417 Ganesh Glory", 8)
    shivalika.projectDetail(3, 2)
    shivalika.upcomingProjects(0)

    println("\n\n")
    val a1 = Adani()
    a1.groupName = "Adani Group"
    a1.totalProjects = 16
    a1.projectName = "Adani Shantigram"
    a1.setContact(123456789)
    a1.showDetails()
    println("Contact number: ${a1.getContact()}")
    a1.upcomingProjects(5)


}

