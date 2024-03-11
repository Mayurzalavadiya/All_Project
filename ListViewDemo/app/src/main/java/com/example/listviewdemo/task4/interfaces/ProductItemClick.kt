package com.example.listviewdemo.task4.interfaces

import android.view.View
import com.example.listviewdemo.task4.pojo.ProductItemData

interface ProductItemClick {

    fun onClick(position: Int, v: View?)
}