package com.example.sample26.pojo.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class UserResponseUnSuccessFull {
    @SerializedName("error")
    @Expose
    var error: String? = null
}