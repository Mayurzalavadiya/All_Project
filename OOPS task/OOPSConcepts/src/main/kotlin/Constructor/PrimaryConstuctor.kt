package Constructor

class PrimaryConstuctor(var name: String, var city: String, var age: Int) {

//    init {
//        println("name: $name city; $city age: $age")
//    }
}

fun main() {
    val pcon = PrimaryConstuctor("Mayur", "Junagadh", 23)
    println("name: ${pcon.name} city: ${pcon.city} age: ${pcon.age}")
    val pcon1 = PrimaryConstuctor(city = "Rajkot", age = 22, name = "Nishit")
    println("name: ${pcon1.name} city: ${pcon1.city} age: ${pcon1.age}")
}