package com.example.kotlin_basics.Task

interface Test {

    fun abc() {
        println("Testing")
    }
}


interface Test1 {

    fun abc() {
        println("Testing1")
    }
}

class Interface : Test, Test1 {
    override fun abc() {
        super<Test>.abc()
        super<Test1>.abc()
    }
}


    open class a {

        open fun test() {
            println("A")
        }

        constructor(name: String) {
            println("Name is $name")
        }
    }

    open class B : a {

        constructor(name: String, Age: Int) : super(name) {
            println("$name, $Age")
        }

        override fun test() {
            super.test()
            println("B")
        }
    }


//class c:b(name){
//
//     fun test1(){
//         super.test()
//        print("C")
//    }
//}

//}

fun main() {
    val xyz = B("Mayur", 23)
    xyz.test()
}