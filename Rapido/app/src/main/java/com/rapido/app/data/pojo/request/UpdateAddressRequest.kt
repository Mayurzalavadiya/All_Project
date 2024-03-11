package com.rapido.app.data.pojo.request

import com.google.gson.annotations.SerializedName

data class UpdateAddressRequest(

    @SerializedName("request_type")
    var requestType: String,

    @SerializedName("address")
    var address: String? = null,

    @SerializedName("street_address")
    var streetAddress: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("state")
    var state: String? = null,

    @SerializedName("zipcode")
    var zipcode: String? = null,

    @SerializedName("address_type")
    var addressType: String? = null,

    @SerializedName("latitude")
    var latitude: String? = null,

    @SerializedName("longitude")
    var longitude: String? = null,

    @SerializedName("address_id")
    var addressId: Double? = null,

    )