package buildingManagement.building

abstract class Apartment : Building() {

    var types: String = "1 BHK"
    var totalUnits: Int = 10
    var isParking: Boolean = false
    var isGarden = false


    override protected fun materials(materialsList: List<String>) {
        println("Materials use in Apartment: ${materialsList}")
    }

    //Overload function
    fun appartmentMaterial(first: String, second: String) {
        materials(listOf(first, second))
    }

    fun appartmentMaterial(first: String, second: String, third: String) {
        materials(listOf(first, second, third))
    }


    override fun showDetails() {
        super.showDetails()
        println("Apartment: \nTypes: ${types + " Appartments"} Total Units: $totalUnits, Parking Available: $isParking Garden Available: $isGarden ")
    }


}

