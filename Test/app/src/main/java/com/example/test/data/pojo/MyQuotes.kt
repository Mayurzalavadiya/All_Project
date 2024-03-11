package com.example.test.data.pojo


data class MyQuotes(
    val image: Int,
    var title: String?,
    var description: String?,
    var location: String?,
    var like: Boolean = false,
    var save: Boolean = false
)