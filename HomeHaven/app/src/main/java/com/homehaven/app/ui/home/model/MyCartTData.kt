package com.homehaven.app.ui.home.model

data class MyCartTData(
    val image: Int,
    val percentage: String,
    val productName: String,
    val productPrice: String,
    val productOldPrice: String,
    val productColor: String,
    var productCount: Int = 1
)
