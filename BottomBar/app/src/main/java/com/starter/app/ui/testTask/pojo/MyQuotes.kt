package com.starter.app.ui.testTask.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MyQuotes(
    val image: Int,
    var title: String?,
    var description: String?,
    var location: String?,
    var like: Boolean = false,
    var save: Boolean = false
):Parcelable