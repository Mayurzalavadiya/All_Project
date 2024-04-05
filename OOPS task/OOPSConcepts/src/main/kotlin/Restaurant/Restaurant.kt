package Restaurant

abstract class Restaurant {
    var name = ""
    var address = ""
    var items = 5
    private  var rating = 0.0
    protected var isOpen = false;

    fun setrating(rating: Double){
        this.rating = rating
    }

    fun getrating():Double{
        return rating
    }

    abstract fun openRestaurant(isopen : Boolean)

    abstract fun restaurantType(type: String)


    open fun itemName(itemname: String, price: Int) {
        println("Item name ${itemname} Price ${price}")
    }

    open fun showdetail(){
        println("Restarument: \nName: ${name} Address: ${address} Rating: ${rating} Available Items: ${items}")
    }
}

fun main(){
    val pun = Punjabi()
    pun.name = "Punjab da paratha"
    pun.address = "Vasant Nagar"
    pun.setrating(4.5)
    pun.items = 25
    pun.punjabi_items = 15
    pun.showdetail()
    pun.itemName("Aloo Paratha",120)
    pun.itemName("Aloo Lehsun Paratha",140)
    pun.itemName("Aloo Cheese Paratha",200)
    pun.itemName("Aloo Cheese Paneer Paratha",220)
    pun.openRestaurant(true)

    println("\n")
    val chainese = Chainese()
    chainese.name = "Food Junction"
    chainese.address = "Jagatpur Rd"
    chainese.setrating(4.3)
    chainese.items = 30
    chainese.chainese_items = 20
    chainese.showdetail()
    chainese.itemsnames("Chowmein","Noodle")
    chainese.itemsnames("Spring Roll", "Paneer Spring Roll", "Mixed Spring Roll")
    chainese.openRestaurant(false)
}