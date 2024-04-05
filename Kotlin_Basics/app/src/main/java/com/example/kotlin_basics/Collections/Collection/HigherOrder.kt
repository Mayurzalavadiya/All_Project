package com.example.kotlin_basics.Collections.Collection

class Laptop {

    interface IsActive {

        fun isOn(): String

        fun isOff(): String

    }


    fun isLaptopWorking(isWorking: Boolean, callback: IsActive): String {
        return if (isWorking) {
            callback.isOn()
        } else {
            callback.isOff()
        }
    }

    fun isLaptopWorking1(isWorking: Boolean, callback: (Boolean) -> String): String {
        return callback.invoke(isWorking)
    }


    val callBack={isWork:Boolean->if (isWork){
        "laptop is on"
    }
    else{
       " laptop is Off"
    }}
}

fun main() {

    val laptop = Laptop()

//********** type 1 ******************//
//    val laptopStatus = laptop.isLaptopWorking(true, object : Laptop.IsActive {
//        override fun isOn(): String {
//            return "laptop is on"
//        }
//
//        override fun isOff(): String {
//            return "laptop is Off"
//        }
//
//    })
//    println("your Laptop Status is: $laptopStatus \n")

//************** type 2 ******************//

//    val laptopStatus = laptop.isLaptopWorking1(true,laptop.callBack)
//    println("your Laptop Status is: $laptopStatus \n")


    //************** type 3 ******************//
    ////Ask to sir about this fun
//    val laptopStatus = laptop.isLaptopWorking1(true){isWork:Boolean->if (isWork){
//        "laptop is on"
//    }
//    else{
//        " laptop is Off"
//    }}
//    println("your Laptop Status is: $laptopStatus \n")
    test() {
        println("do something")
        3
    }

    val callback = { n: Int -> n * n }

    val multiply = One(4, callback)
    println(multiply)


    val higher = HigherOrder()
    val addition: (Int, Int) -> Int = { x, y -> x + y }
    higher.myFun(2, 2, addition)
}


fun test(doSomething: () -> Unit) {
    doSomething.invoke()
}

val three = { n: Int -> n + n }


fun One(X: Int, callback: (Int) -> Int): Int {
//    println(callback.invoke(X))
    return callback(three(X))
}


class HigherOrder {
    fun myFun(x: Int, y: Int, addition: (Int, Int) -> Int): Int {
        println("printf " + addition.invoke(x, y))
        return addition(x, y)
    }
}