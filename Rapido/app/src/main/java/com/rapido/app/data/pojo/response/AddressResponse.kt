package com.rapido.app.data.pojo.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressResponse(

    @SerializedName("address")
    val address: String? = null,
    @SerializedName("address_type")
    val addressType: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Double? = null,
    @SerializedName("insert_at")
    val insertAt: String? = null,
    @SerializedName("is_default")
    val isDefault: Double? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("street_address")
    val streetAddress: String? = null,
    @SerializedName("update_at")
    val updateAt: String? = null,
    @SerializedName("user_id")
    val userId: Double? = null,
    @SerializedName("zipcode")
    val zipcode: Double? = null
) : Parcelable
