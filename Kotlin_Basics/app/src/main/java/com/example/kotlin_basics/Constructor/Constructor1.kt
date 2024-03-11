package com.example.kotlin_basics.Constructor

class Parent {
    var name : String

    constructor(name: String) {
        this.name = name
    }
}

fun main(){

    val parent = Parent("Mayur")
}