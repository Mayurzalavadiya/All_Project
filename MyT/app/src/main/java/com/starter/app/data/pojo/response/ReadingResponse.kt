package com.starter.app.data.pojo.response


import com.google.gson.annotations.SerializedName

class ReadingResponse : ArrayList<Reading>()

data class Reading(
    @SerializedName("background_color")
    val backgroundColor: String? = null,
    @SerializedName("care_img")
    val careImg: String? = null,
    @SerializedName("color_code")
    val colorCode: String? = null,
    @SerializedName("color_code_1")
    val colorCode1: String? = null,
    @SerializedName("color_code_2")
    val colorCode2: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("default_reading")
    val defaultReading: String? = null,
    @SerializedName("graph")
    val graph: String? = null,
    @SerializedName("home_img")
    val homeImg: String? = null,
    @SerializedName("image_icon_url")
    val imageIconUrl: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("img_extn")
    val imgExtn: String? = null,
    @SerializedName("information")
    val information: String? = null,
    @SerializedName("is_active")
    val isActive: String? = null,
    @SerializedName("is_deleted")
    val isDeleted: String? = null,
    @SerializedName("keys")
    val keys: String? = null,
    @SerializedName("mandatory")
    val mandatory: String? = null,
    @SerializedName("max_limit")
    val maxLimit: String? = null,
    @SerializedName("measurements")
    val measurements: String? = null,
    @SerializedName("min_limit")
    val minLimit: String? = null,
    @SerializedName("not_configured")
    val notConfigured: String? = null,
    @SerializedName("reading_name")
    val readingName: String? = null,
    @SerializedName("reading_order")
    val readingOrder: Double? = null,
    @SerializedName("readings_master_id")
    val readingsMasterId: String? = null,
    @SerializedName("standard_info")
    val standardInfo: String? = null,
    @SerializedName("standard_max")
    val standardMax: String? = null,
    @SerializedName("standard_min")
    val standardMin: String? = null,
    @SerializedName("sub_readings")
    val subReadings: String? = null,
    @SerializedName("unit_data")
    val unitData: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("updated_by")
    val updatedBy: String? = null
)
