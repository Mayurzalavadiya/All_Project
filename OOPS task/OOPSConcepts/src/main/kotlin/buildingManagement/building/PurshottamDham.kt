package buildingManagement.building

class PurshottamDham : Apartment() {

    var Ownername = ""
    var isLIft = true
    private var rating = 4.2

    fun buildupArea(squreft:Int, price: Int){
        println("total sq.ft: ${squreft} sq.ft  price: ${price}")
    }

    override fun showDetails() {
        super.showDetails()
        println("Flats Details:\nFlate Owner name: ${Ownername} lift available: ${isLIft} flat Rating: ${rating}")
    }
}