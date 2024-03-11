package com.rapido.app.data.pojo

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("country_code")
    val countryCode: String? = null,
    @SerializedName("device_id")
    val deviceId: String? = null,
    @SerializedName("device_type")
    val deviceType: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("last_login")
    val lastLogin: String? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("profile_image")
    val profileImage: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("unread_notification_count")
    val unreadNotificationCount: Double? = null,
    @SerializedName("user_id")
    val userId: Double? = null,
    @SerializedName("user_language")
    val userLanguage: String? = null,
    @SerializedName("wp_country_code")
    val wpCountryCode: String? = null,
    @SerializedName("wp_phone")
    val wpPhone: String? = null
) {

    companion object {
        const val KEY = "user"
    }
}