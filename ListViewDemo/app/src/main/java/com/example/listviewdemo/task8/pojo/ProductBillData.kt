package com.example.listviewdemo.task8.pojo

data class ProductBillData(
    val id: Int?,
    val name: String?,
    var price: Double,
    var isSelected: Boolean = false,
)