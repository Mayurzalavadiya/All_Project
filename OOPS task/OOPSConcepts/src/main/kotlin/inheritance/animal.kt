package inheritance

open class animal {
    open fun run(abc : String){
        println("animal running....")
    }


}
class dog : animal(){

//    val aaa = animal()


    override fun run(abc : String){
        println("dog running....")
    }

}
