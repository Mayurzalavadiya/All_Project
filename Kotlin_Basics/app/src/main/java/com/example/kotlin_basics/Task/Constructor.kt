package com.example.kotlin_basics.Task

class DefaultConstructor {

    init {
        println("Default Constructor")
    }
}

class PrimaryCons(val name: String) {

    init {
        println(name)
    }
}

class SecondaryCons() {

    constructor(name: String, color:String) :this()  {
        println("My name is $name")
    }
    constructor(name: String):this() {
        println("My name is $name")
    }

    init {
        println("Secondary Constructor")
    }

    constructor(name: String, age: Int, color:String) : this(name, color ) {
        println("$name, $age")
    }

}


fun main() {

    val default = DefaultConstructor()
    val primary = PrimaryCons("Mayur")
    val Second = SecondaryCons("Mayur", 25,"Red")


}