package com.example.sample26.pojo.request


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class UserLoginRequest {
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
}