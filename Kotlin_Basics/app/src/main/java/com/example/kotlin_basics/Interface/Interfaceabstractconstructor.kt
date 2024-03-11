package com.example.kotlinbasics.Interface


class Mobile:Storage,Simport{

    lateinit var Storage :String
    constructor(name: String){
        println("mobile name is $name")
    }

    constructor(name: String, price :Int, storage: String){
        this.Storage = storage
        println("mobile name is $name & it's price is $price")
    }

    override fun Storage() {
        println("Available Storage is $Storage")
    }

    override fun isSimport() {
        println("Dual Sim port Available")
    }
}

class Laptop: Storage, Harddisk{

    constructor(name: String): super(name){
        println("laptop name is $name")
    }

    constructor(name: String, processor: String, ram:Int):this(name){
        println("laptop name is $name \nfacelities like:-\nprocessoy $processor \nRAM: $ram GB" )
    }

    override fun Storage() {
       println("Storage is 1 TB")
    }

    override fun ssd() {
        println("SSD available")
    }
}


interface Storage{

    fun Storage()

    fun Screen(){
        println("LCD or AMOLED ( LCD display if fragile, easily breaks)")
    }
}


abstract class Harddisk(val name:String){

    fun companyName(){
        println("${name} company")
    }
    abstract fun ssd()
}

abstract class Simport{

    abstract fun isSimport()
}



fun main(){
//    val mobile = Mobile("Samsung",15000,"8 GB")
//    mobile.Storage()
//    mobile.isSimport()

    val laptop = Laptop("Dell"," Intel ",8)
    laptop.Storage()
    laptop.ssd()
    laptop.companyName()
}