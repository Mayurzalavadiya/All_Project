package com.example.listviewdemo.task1.interfaces

import android.view.View
import com.example.listviewdemo.pojo.ItemData

interface RecyclerGridClickItem {
    fun onClick(item: ItemData?, v: View?, position:Int)
}