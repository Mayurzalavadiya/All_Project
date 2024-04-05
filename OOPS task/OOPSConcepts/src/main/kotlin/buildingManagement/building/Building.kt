package buildingManagement.building

abstract class Building {
    var name: String = ""
    var address: String = ""
    var floors: Int = 1

    // Abstract function
    abstract protected fun materials(materialsList: List<String>)

    // Override function
    open fun showDetails() {
        println("Building \nName: $name, Address: $address, Floors: $floors")
    }
}

fun main() {

    // Using Apartment class (subclass of Building)
//    val apartment = Apartment()
//    apartment.name = "Luxury Apartments"
//    apartment.address = "123 Park Avenue"
//    apartment.floors = 15
//    apartment.types = "2 & 3 BHK"
//    apartment.totalUnits = 100
//    apartment.isParking = true
//    apartment.isGarden = false
//    apartment.showDetails()
//    apartment.appartmentMaterial("Cement", "Concrete", "Bricks")

    println("\n\n")

    // Using Tournament class (subclass of Building)
    val tarnament = Tarnament()
    tarnament.name = "Shree RaheKrushn Kunj"
    tarnament.address = "103 Auda Garden"
    tarnament.floors = 2
    tarnament.showDetails()
    tarnament.setisSportground(true)
    tarnament.setSportlist(listOf("Football", "Basketball", "Tennis"))
    tarnament.tarnamenttMaterial("Ceramic", "Stone", "Plastic", "Glass", "Steel")

    println("\n\n")

    val purshottamdham = PurshottamDham()
    purshottamdham.name = "PurshottamDham Flats"
    purshottamdham.address = "Near Croma, Thaltej"
    purshottamdham.floors = 3
    purshottamdham.types = "2 & 3 BHK"
    purshottamdham.totalUnits = 24
    purshottamdham.isParking = true
    purshottamdham.Ownername = "Rajubhai"
    purshottamdham.isLIft = false
    purshottamdham.showDetails()
    purshottamdham.appartmentMaterial("Wood", "Steel")
    purshottamdham.buildupArea(3800, 45000)

}