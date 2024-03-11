package com.rapido.app.data

import okhttp3.HttpUrl

object URLFactory {

    // server details
//    https://api.rapidocostarica.com:7072/api/v1/user/login
    private const val IS_LOCAL = true
    private const val SCHEME = "https"
    private val HOST = if (IS_LOCAL) "api.rapidocostarica.com" else "skkyn.com"
    private val API_PATH = if (IS_LOCAL) "api/v1/" else "websitedata/api/v2/"
    private val PORT = if (IS_LOCAL) 7072 else 8082

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .port(PORT)
                .addPathSegments(API_PATH)
                .build()
    }

    // API Methods
    object Method {
        const val LOGIN = "user/login"
        const val SIGN_UP = "user/signup"
        const val GET_OTP = "user/get_otp"
        const val GET_ADDRESS = "user/get_address_list"
        const val UPDATE_ADDRESS = "user/update_address"
    }

}
