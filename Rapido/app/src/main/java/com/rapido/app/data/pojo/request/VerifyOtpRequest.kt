package com.rapido.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class VerifyOtpRequest(

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("country_code")
    var countryCode: String,

    @SerializedName("wp_country_code")
    var wpCountryCode: String? = null,

    @SerializedName("phone")
    var phone: String,

    @SerializedName("wp_phone")
    var wpPhone: String? = null,

    @SerializedName("type")
    var type: String,
)