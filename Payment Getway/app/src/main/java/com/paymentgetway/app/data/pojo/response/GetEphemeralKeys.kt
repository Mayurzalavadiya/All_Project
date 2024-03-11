package com.paymentgetway.app.data.pojo.response


import com.google.gson.annotations.SerializedName

data class GetEphemeralKeys(
    @SerializedName("associated_objects")
    val associatedObjects: List<AssociatedObject?>? = null,
    @SerializedName("created")
    val created: Int? = null,
    @SerializedName("expires")
    val expires: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("livemode")
    val livemode: Boolean? = null,
    @SerializedName("object")
    val objectX: String? = null,
    @SerializedName("secret")
    val secret: String? = null
) {
    data class AssociatedObject(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("type")
        val type: String? = null
    )
}