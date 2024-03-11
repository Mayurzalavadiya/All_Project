package com.example.kotlin_basics.Task

import com.google.gson.Gson


fun main() {
    val mainListing = ArrayList<Demo>(listOf(Demo(), Demo(), Demo()))

    /* val internalListing = ArrayList<Int>()
     for (demo in mainListing) {
         for (child in demo.childs) {
             if(child.age >= 18){
                 child.age = 26
             }
             if (child.age > 25) {
                 for (integer in child.listing) {
                     val finalAnswer = integer + 2
                     if (finalAnswer > 500) {
                         internalListing.add(finalAnswer)
                     }
                 }
             }

         }
     }*/
    println(Gson().toJson(mainListing))

    val internalListing = mainListing
        .flatMap { demo -> demo.childs }
        .map { child ->
            if (child.age >= 18) {
                child.age = 26
            }
            child
        }
        .filter { child -> child.age > 25 }
        .flatMap { child -> child.listing }
        .map { number -> number + 2 }
        .filter { number -> number > 500 }
            as ArrayList<Int>

    println(Gson().toJson(internalListing))
}

class Demo {
    val childs = ArrayList<Child>(listOf(Child(18), Child(7), Child(26)))
}

class Child(var age: Int) {
    val name = ""
    val listing = ArrayList<Int>(listOf(2341, 4567, 98, 453, 322))
}