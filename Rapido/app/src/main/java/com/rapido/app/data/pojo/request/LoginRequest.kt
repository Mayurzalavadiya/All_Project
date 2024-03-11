package com.rapido.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("email")
    var email: String? = null,

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

    @SerializedName("app_version")
    var appVersion: String? = null,

    @SerializedName("uuid")
    var uuid: String? = null,
)