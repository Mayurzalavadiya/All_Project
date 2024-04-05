package com.example.listviewdemo.demo.interfaces

import android.view.View
import com.example.listviewdemo.pojo.RecyclerItemData

interface RecyclerClickItem {
    fun onClick(item: RecyclerItemData?, v: View?)
}