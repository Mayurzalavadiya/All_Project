package com.paymentgetway.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class EphemeralKeysRequest(
    @SerializedName("customer")
    var customer: String,
)