package DataClass

data class User(var name : String, var email: String)


fun main(){
    val user1 = User("Mayur","mayurz@hlis.net.in")
    println(user1)

    val user2 = User("Mayur","mayurz@hlis.net.in")
    println(user1==user2)
    println(user1.equals(user2))


    val user3 = user1.copy()
    println(user3)

    val user4 = user1.copy(email = "mayurz@hyperlink.in")
    println(user4)
}