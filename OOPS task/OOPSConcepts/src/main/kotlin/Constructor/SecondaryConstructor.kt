package Constructor

class SecondaryConstructor(name: String, city: String, age: Int) {

    constructor(name: String, city: String, age: Int, isSttudent: Boolean) : this(name, city, age) {
        println("Secondary Constructor name: $name cty: $city age: $age Student: $isSttudent")
    }

    init {
        println("Secondary Constructor name: $name cty: $city age: $age")
    }
}

fun main(){

    val second = SecondaryConstructor("mayur","Junagadh",23)
    val second1 = SecondaryConstructor("Nishit","rajkot",23, false)
//    println("Secondary Constructor name: ${second1.name} cty: ${second1.city} age: ${second1.age}")
}