package Testing

class Test(){
    var experience = 0
    var Company = ""
    var name = ""
    var address = ""
    private var age = 23;
//    var experience = 12

    fun setage(a:Int){
        this.age = a
    }
    fun getage():Int {
        return age
    }

    constructor(company: String, name: String,experience: Int,address: String) : this() {
        this.experience = experience
        this.Company = company
        this.name = name
        this.address=address
    }
}


open class a{
    fun test(){
        println("testing....")
    }
}

open class b{
    fun test(){
        println("testing....")
    }
}


fun main(){

    var test = Test()
    test.setage(25)
    println("Age : ${test.getage()}")
}