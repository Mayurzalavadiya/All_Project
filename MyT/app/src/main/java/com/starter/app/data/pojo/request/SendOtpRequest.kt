package com.starter.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class SendOtpRequest(
    @SerializedName("contact_no")
    var contactNo: String,


)