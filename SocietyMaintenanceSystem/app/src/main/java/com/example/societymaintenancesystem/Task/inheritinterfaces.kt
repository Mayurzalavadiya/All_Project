package com.example.societymaintenancesystem.Task


interface animal {

    fun eat()
    fun drink()
    fun run(){
        println("It can be running")
    }
}

//interface to interface
interface human  {
    fun walk()
    fun run()
}


interface vehical {
    fun listingSongs()
    fun ride()


}


//Multiple
class boy : human,animal{
    override fun walk() {
        println("Boy can walking")
    }

    override fun run() {
        super<animal>.run()
       println("Boy can running")
    }


    override fun eat() {
        println("Boy can eating")
    }

    override fun drink() {
        println("Boy can drinking")
    }

}

//hierarchical
class girl:human{
    override fun walk() {
        println("girl can walking")
    }

    override fun run() {
        println("girl can running")
    }
}

//single
abstract class car:vehical{
    override fun listingSongs() {
        println("we can listing Songs in car")
    }

}

//multilevel
class elephant : animal,car() {
    override fun eat() {
        println("Elephant can eating")
    }

    override fun drink() {
        println("Elephant can drinking")
    }

    override fun ride() {
        println("we can riding elephant")
    }


}

fun main() {
  val boy = boy()
    boy.run()
    boy.drink()
    boy.eat()
    boy.drink()

    val girl = girl()
    girl.run()
    girl.walk()

    val elephant = elephant()
    elephant.eat()
    elephant.drink()
    elephant.ride()
    elephant.listingSongs()
}