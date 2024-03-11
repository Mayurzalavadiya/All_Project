package com.starter.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class LoginVerifyOtpRequest(

    @SerializedName("contact_no")
    var contactNo: String,

    @SerializedName("otp")
    var otp: String,
    )