package com.starter.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(

    @SerializedName("email")
    var email: String,

    @SerializedName("name")
    var name: String,
    )