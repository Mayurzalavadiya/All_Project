package com.starter.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class UpdateAddressRequest(

    @SerializedName("address")
    var address: String,

    )