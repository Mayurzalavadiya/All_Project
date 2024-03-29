package com.tinder.app.core

import com.tinder.app.data.pojo.User

interface Session {

    var apiKey: String

    var userSession: String

    var userId: String

    val deviceId: String

    var user: User?

    val language: String

    fun clearSession()

    companion object {
        const val API_KEY = "api-key"
        const val USER_SESSION = "token"
        const val USER_ID = "USER_ID"
        const val DEVICE_TYPE = "A"
        const val LANGUAGE = "accept-language"
    }
}
