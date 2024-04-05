package com.example.societymaintenancesystem.Task

import com.google.gson.Gson
import kotlin.collections.ArrayList
import kotlin.collections.ArrayList as ArrayList1


 class Recipe(
    val recipeName: String,
    val recipeNumber: Int,
    val cashierName: String,
    val cashierNumber: Long,
    val ingredients: HashMap<String, HashMap<String,Int>>,
    val favorite: Boolean
){ }


fun main() {
    val mainRecipeList = arrayListOf(
        Recipe(
            "Sweets Items",
            101,
            "Raju",
            1235404541,
//            hashMapOf("Peda" to 10, "Aamras" to 5, "Gajar ka Halwa" to 8, "Kaju Katli" to 4),
            hashMapOf("Peda" to  hashMapOf("sdsd" to 5)),
            true
        ),
//        Recipe(
//            "Paneer Recipes",
//            5,
//            "Nishit",
//            5355344,
////            hashMapOf(
////                "Malai Paneer" to 1,
////                "Paneer Pulao" to 8,
////                "Methi Paneer" to 4,
////                "Paneer Makhani" to 6,
////                "Paneer Paratha" to 5
////            ),
//            false
//        ),
//        Recipe(
//            "Amul",
//            5,
//            "Nishit",
//            5355344,
////            hashMapOf(
////                "Amul Milk" to 4,
////                "Butter" to 2,
////                "Cheese" to 8,
////            ),
//            false
//        ), Recipe(
//            "Lapinos Pizza",
//            101,
//            "Raju",
//            1235404541,
////            hashMapOf("Onion" to 1, "Tomato" to 2, "Chilli" to 3, "margherita" to 6),
//            true
//        )
    )

    println("all Recipes List")

    for (i in 0 until mainRecipeList.size) {
        println(Gson().toJson(mainRecipeList[i]))
    }
    println()

    val moreThanThree = mainRecipeList.filter { recipe -> recipe.ingredients.size > 3 }
    println("more than Three ingredients")
    for (i in moreThanThree.indices) {
        println(moreThanThree[i])
    }
    println()

//    val moreThanFive =
//        mainRecipeList.flatMap { recipe -> recipe.ingredients.map { it->it }.filter { it.value > 5 } }
//    println("more than Five Quantity")
//    for (i in moreThanFive.indices) {
//        println(moreThanFive[i])
//    }
    println()

    val favorite = mainRecipeList.filter { it -> it.favorite }
    println("recipes which are marked as favorite")
    for (i in favorite.indices) {
        println(favorite[i])
    }
    println()

    val recipeName = mainRecipeList.flatMap { recipe -> recipe.recipeName.split(" ") }

    println("recipes Name")
    println(recipeName)

}
