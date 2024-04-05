package com.example.sample26.pojo.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class UserRegisterResponseSuccessFull {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
}