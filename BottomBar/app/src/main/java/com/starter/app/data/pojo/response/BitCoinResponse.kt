package com.starter.app.data.pojo.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class BitCoinResponse(
    @SerializedName("bpi")
    @Expose
    val bpi: Bpi? = null,
    @SerializedName("chartName")
    @Expose
    val chartName: String? = null,
    @SerializedName("disclaimer")
    @Expose
    val disclaimer: String? = null,
    @SerializedName("time")
    @Expose
    val time: Time? = null
) {
    class Bpi(
        @SerializedName("EUR")
        @Expose
        val eUR: EUR? = null,
        @SerializedName("GBP")
        @Expose
        val gBP: GBP? = null,
        @SerializedName("USD")
        @Expose
        val uSD: USD? = null
    ) {
        class EUR(
            @SerializedName("code")
            @Expose
            val code: String? = null,
            @SerializedName("description")
            @Expose
            val description: String? = null,
            @SerializedName("rate")
            @Expose
            val rate: String? = null,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double? = null,
            @SerializedName("symbol")
            @Expose
            val symbol: String? = null
        )

        class GBP(
            @SerializedName("code")
            @Expose
            val code: String? = null,
            @SerializedName("description")
            @Expose
            val description: String? = null,
            @SerializedName("rate")
            @Expose
            val rate: String? = null,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double? = null,
            @SerializedName("symbol")
            @Expose
            val symbol: String? = null
        )

        class USD(
            @SerializedName("code")
            @Expose
            val code: String? = null,
            @SerializedName("description")
            @Expose
            val description: String? = null,
            @SerializedName("rate")
            @Expose
            val rate: String? = null,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double? = null,
            @SerializedName("symbol")
            @Expose
            val symbol: String? = null
        )
    }

    class Time(
        @SerializedName("updated")
        @Expose
        val updated: String? = null,
        @SerializedName("updatedISO")
        @Expose
        val updatedISO: String? = null,
        @SerializedName("updateduk")
        @Expose
        val updateduk: String? = null
    )
}