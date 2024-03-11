package com.rapido.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(

    @SerializedName("full_name")
    var fullName: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("country_code")
    var countryCode: String? = null,

    @SerializedName("wp_country_code")
    var wpCountryCode: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("wp_phone")
    var wpPhone: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("latitude")
    var latitude: String? = null,

    @SerializedName("longitude")
    var longitude: String? = null,

    @SerializedName("device_type")
    var deviceType: String,

    @SerializedName("device_id")
    var deviceId: String,

    @SerializedName("device_name")
    var deviceName: String? = null,

    @SerializedName("model_name")
    var modelName: String? = null,

    @SerializedName("os_version")
    var osVersion: String? = null,

    @SerializedName("uuid")
    var uuid: String? = null,
)