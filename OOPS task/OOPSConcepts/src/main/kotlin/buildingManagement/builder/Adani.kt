package buildingManagement.builder

class Adani : Builder() {

    private var contactno = 1111111;

    fun setContact(number: Int) {
        this.contactno = contactno
    }

    fun getContact(): Int {
        return contactno
    }

    override fun upcomingProjects(number: Int) {
        println("Upcoming Projects: ${number}")
    }
}