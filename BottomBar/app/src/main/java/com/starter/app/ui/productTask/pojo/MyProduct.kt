package com.starter.app.ui.productTask.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.MultipartBody


@Parcelize
data class MyProduct(
    val position: String? = null,
    val image: String?,
    var courseName: String?,
    var startDate: String?,
    var endDate: String?,
    var description: String?,
    var price: String?,
) : Parcelable