package Restaurant

class Chainese : Restaurant() {
    var chainese_items = 2
    override fun openRestaurant(isopen: Boolean) {
        this.isOpen = isOpen
        if (isopen){
            println("$name is open now, Welcome to our Chainese restaurant!")
        }
        else{
            println("$name is close now")
        }
    }

    override fun restaurantType(type: String) {
        println("Chainese restaurant Type ${type}")
    }

    fun itemsnames(item1: String, item2: String) {
        println("Chainese Items name 1) ${item1} 2) ${item2}}")
    }

    fun itemsnames(item1: String, item2: String, item3: String) {
        println("Chainese Items name 1) ${item1} 2) ${item2} 3) ${item3}}")
    }

}