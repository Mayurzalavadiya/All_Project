package com.example.kotlinbasics.Parents

abstract class Parents() {

    var name = "Mayur"


    abstract fun absMethod()

    init {
//        println("init My name is $n")
        println("init My name is $name")

    }

    constructor(name:String) : this("Mayur",45) {
        println("My name is $name")
    }

    constructor(name: String, age: Int) : this() {
        println("$name & $age")
    }
}


abstract class Child : Parents {

    constructor(name: String) : super(name) {
        println("Child class call $name")
    }

//    override fun absMethod() {
//        println("Child abs method")
//    }
}

class SubChile : Child {

    constructor() : super("SubChile"){
        println("Sub-child constructor")
    }


    constructor(name: String) : super(name) {
        println("SubChile : $name")
    }

    override fun absMethod() {
        println("Subchild call")
    }

}

//class Student (var name: String) {
//
//    init {
//        println("Student has got a name as $name")
//    }
//
//    constructor(sectionName: String, id: Int) :this(sectionName) {
//        println("$sectionName $id")
//    }
//}

fun main() {

    val con = SubChile()
    con.absMethod()
//    val study = Student("Mayur",20)


}