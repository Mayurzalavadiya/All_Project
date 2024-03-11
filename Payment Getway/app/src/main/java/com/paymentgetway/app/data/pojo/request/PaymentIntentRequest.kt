package com.paymentgetway.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class PaymentIntentRequest(
    @SerializedName("customer")
    var customer: String,

    @SerializedName("amount")
    var amount: String,

    @SerializedName("currency")
    var currency: String = "inr",

    @SerializedName("automatic_payment_methods")
    var automaticPaymentMethods: AutomaticPaymentMethods = AutomaticPaymentMethods(true)
)

data class AutomaticPaymentMethods(
    @SerializedName("enabled")
    var enabled: Boolean
)