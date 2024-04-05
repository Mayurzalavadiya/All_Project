package com.example.kotlin_basics.PatternTask

fun main(){

    var range = 5
    range *= 3
    val mid = range / 2
    for (i in 0 until range) {
        for (k in 0 until range) {
            if ((i < range/3 || i>= range - range/3 || k < range/3 || k >= range - range/3) &&
                ((i<mid && (k in mid - i until mid + i + 1)) ||
                        (i>=mid && (k in i - mid until range - i + mid)))
            ) {
                print("*  ")
            } else {
                print("   ")
            }
        }

        println()
    }
}