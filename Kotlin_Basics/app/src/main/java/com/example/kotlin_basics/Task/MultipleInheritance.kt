package com.example.kotlin_basics.Task



interface abc{

    fun walk()
}


interface pqr:abc{

    fun drink()
}
class MultipleInheritance:pqr {
    override fun drink() {

    }

    override fun walk() {

    }

}




