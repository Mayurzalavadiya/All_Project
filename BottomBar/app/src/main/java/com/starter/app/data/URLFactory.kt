package com.starter.app.data

import okhttp3.HttpUrl

object URLFactory {

    // server details
//    private const val IS_LOCAL = true
//    private const val SCHEME = "http"
//    private val HOST = if (IS_LOCAL) "18.169.15.187" else "skkyn.com"
//    private val API_PATH = if (IS_LOCAL) "api/v1/" else "websitedata/api/v2/"
//    private val PORT = if (IS_LOCAL) 8082 else 8082

    private const val IS_LOCAL = true
    private const val SCHEME = "http"
    private val HOST = if (IS_LOCAL) "65.1.60.241" else "skkyn.com"
    private val API_PATH = if (IS_LOCAL) "api/v1/common/" else "websitedata/api/v2/"
    private val PORT = if (IS_LOCAL) 8272 else 8082

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .port(PORT)
                .addPathSegments(API_PATH)
                .build()
    }


    private const val SCHEME1 = "https"
    private const val HOST1 = "api.coindesk.com"
    private const val API_PATH1 = "v1/bpi/"

    fun provideHttpUrl1(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME1)
            .host(HOST1)
            .addPathSegments(API_PATH1)
            .build()
    }



    // API Methods
    object Method {
        const val LOGIN = "user/login"
        const val GET_STATE = "get-state-list"
        const val BIT_COIN = "currentprice.json"
    }

}
