package buildingManagement.building

class Tarnament  : Building() {

    private var isSportGround = false
    private var sportsList: List<String> = listOf("")

    fun setisSportground(isSportGround : Boolean){
        println("Tarnament: \nSport Ground Available: ${isSportGround}  ")
        this.isSportGround = isSportGround
        if (isSportGround){
            println("Enter Sports name")
        }
        else {
            println("Sports Ground not available")
        }
    }

    fun setSportlist(sportList:List<String>){
        this.sportsList = sportList
        println("Sports Played: ${sportsList.joinToString(", ")}")
    }

    override protected fun materials(materialsList: List<String>) {
        println("Materials use in Tarnament: ${materialsList}")
    }

    //Overload function
    fun tarnamentMaterial(first: String, second: String) {
        materials(listOf(first, second))
    }

    fun tarnamenttMaterial(first: String, second: String, third: String, forth: String, fifth: String) {
        materials(listOf(first, second, third, forth, fifth))
    }


}