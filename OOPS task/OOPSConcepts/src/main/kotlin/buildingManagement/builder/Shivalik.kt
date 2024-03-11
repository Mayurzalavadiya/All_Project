package buildingManagement.builder


open class Shivalika : Builder() {
    private var name : String = ""
    private var age : Int = 23
    private var address : String = ""

    fun setName(name:String){
        this.name = name
    }

    fun setAge(age : Int){
        this.age = age
    }

    fun setAddress(address :String){
        this.address = address
    }

    override fun upcomingProjects(number: Int) {
        println("Upcoming Projects: ${number}")
    }


    override fun showDetails() {
        super.showDetails()
        println(
            "Personal Information: \nName: ${name} Age: ${age} Address:  ${address}  Experience ${experience}"
        )
    }
}