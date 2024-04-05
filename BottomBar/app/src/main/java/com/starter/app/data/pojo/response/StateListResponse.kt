package com.starter.app.data.pojo.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class StateListResponse : ArrayList<State>()

data class State(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)
