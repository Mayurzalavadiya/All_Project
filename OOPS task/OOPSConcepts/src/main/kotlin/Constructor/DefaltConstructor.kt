package Constructor

//Default Constructor
class Constructor {
    var name: String? = null

    constructor() {
        println("value of name ${name} ")
    }

    companion object {
        var n = 0;
    }


    init {
        println("init value of N ${n}")
        n++
        println("init value of N ${n}")

    }
}

fun main() {

    val con = Constructor()
    println("value of name ${con.name} ")


//    println(con.n)           // error, we don't access directly
//    println(Constructor.n)   // only access by class
}