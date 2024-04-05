package Restaurant

class Punjabi: Restaurant() {
    var punjabi_items = 2
    private var price = 0

    override fun openRestaurant(isopen: Boolean) {
        this.isOpen = isOpen
        if (isopen){
            println("$name is open now, Welcome to our Punjabi restaurant!")
        }
        else{
            println("$name is close now")
        }
    }

    override fun restaurantType(type: String) {
        println("restaurant Type ${type}")
    }

    override fun itemName(itemname : String, price: Int){
        this.price = price
        println("Item name ${itemname} Price ${price}")
    }

    override fun showdetail(){
        super.showdetail()
        println("Punjabi Items: ${punjabi_items} Rating ${getrating()}")
    }
}