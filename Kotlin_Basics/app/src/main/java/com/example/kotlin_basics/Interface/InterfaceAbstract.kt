package com.example.kotlinbasics.Interface


class Animal : Run, Walk() {
    override fun walk() {
        println("Animal can Walk")
    }

    override fun drink() {
        println("Animal can Drink")
    }

    override fun run() {
        println("Animal can Run")
    }
}

class Vehicle : Run, Change_part() {
    override fun run() {
        println("Vehical can Running")
    }

    override fun changePart() {
        println("Vehical part chanageable")
    }

}

class Human : Run, Walk() {
    override fun run() {
        println("human can Running")
    }

    override fun walk() {
        println("human can Walking")
    }

    override fun drink() {
        println("human can Drinking")
    }


}


interface Run {
    fun run()
    fun stop() {
        println("Stop Interface")
    }
}


abstract class Walk {
    abstract fun walk()
    abstract fun drink()
}


abstract class Change_part {
    abstract fun changePart()
}

fun main(){

    val animal = Animal()
    animal.run()
    animal.walk()
    animal.drink()

    val human = Human()
    human.run()
    human.walk()
    human.drink()


    val vehicle = Vehicle()
    vehicle.run()
    vehicle.changePart()
}