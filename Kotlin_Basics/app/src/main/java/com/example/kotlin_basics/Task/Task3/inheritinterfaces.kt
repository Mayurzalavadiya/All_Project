package com.example.societymaintenancesystem.Task


interface Animal {

    fun eat()
    fun drink()
    fun run(){
        println("It can be running")
    }
}

//interface to interface
interface Human  {
    fun walk()
    fun run()
}


interface Vehical {
    fun listingSongs()
    fun ride()


}


//Multiple
class Boy : Human,Animal{
    override fun walk() {
        println("Boy can walking")
    }

    override fun run() {
        super<Animal>.run()
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
class Girl:Human{
    override fun walk() {
        println("girl can walking")
    }

    override fun run() {
        println("girl can running")
    }
}

//single
abstract class Car:Vehical{
    override fun listingSongs() {
        println("we can listing Songs in car")
    }


   abstract fun drink()
}

//multilevel
class Elephant : Animal,Car() {
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
  val boy = Boy()
    boy.run()
    boy.drink()
    boy.eat()

    val girl = Girl()
    girl.run()
    girl.walk()

    val elephant = Elephant()

    elephant.drink()

}