package com.example.kotlin_basics.EnumClass

enum class Vehicle(val tyre:Int) {

    CAR(4) {
        override fun tyre() {
            println("car have $tyre tyre")
        }
    },
    BUS(6) {
        override fun tyre() {
            println("Bus have $tyre tyre")
        }
    },
    TRUCK(8) {
        override fun tyre() {
            println("Truck have $tyre tyre")        }
    },
    MOTORCYCLE(2) {
        override fun tyre() {
            println("Motorcycle have $tyre tyre")        }
    },
    AUTORIKSHA(3) {
        override fun tyre() {
            println("Autoriksha have $tyre tyre")        }
    };

  abstract fun tyre()

}

fun main() {
    Vehicle.TRUCK.tyre()
}