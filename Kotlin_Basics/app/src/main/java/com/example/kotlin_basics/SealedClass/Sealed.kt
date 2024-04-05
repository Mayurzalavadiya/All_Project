package com.example.kotlin_basics.SealedClass

import com.example.kotlin_basics.Collections.Collection.test


class Test{
    private constructor(a:Int){}
    private constructor(a:Int,y:String){}
}
sealed class Demo(val isnum:Boolean) {

    class A(val num:Int):Demo(true){
        fun double(){
            println("values is ${num*num}")
        }
    }

    fun sub(num:Int){
        println( println("values is ${num-5}"))
    }

    class B(val num:Int){
        fun additional(){
            println("values is ${num+num}")
        }
    }
}

class Extend:Demo(false){
//    override fun div() {
//        TODO("Not yet implemented")
//    }

}

fun main(){
    val multiply = Demo.A(5)
    multiply.double()
//
    val addition = Demo.B(5)
    addition.additional()

//    val test = Test(5)
}