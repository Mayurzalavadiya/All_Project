import DataClass.User




const val lastName: String = "bbb"

class Test{

        val firstName: String

        lateinit var name :String



init {
    firstName = "Ajay"
}




}

fun main(){


    val user = Test()
    if (user.firstName is String)
        println(println("${user.firstName} is a String"))


    println(user.firstName)
    println(lastName)

}