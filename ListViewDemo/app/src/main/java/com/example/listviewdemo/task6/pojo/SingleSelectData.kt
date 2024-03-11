package com.example.listviewdemo.task6.pojo

import android.os.Parcel
import android.os.Parcelable

data class SingleSelectData(
    val image: String?,
    val name: String?,
    val des: String?,
    var isSelected: Boolean = false,

    )