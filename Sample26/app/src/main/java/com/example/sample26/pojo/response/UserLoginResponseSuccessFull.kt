package com.example.sample26.pojo.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class UserLoginResponseSuccessFull {
    @SerializedName("token")
    @Expose
    var token: String? = null
}