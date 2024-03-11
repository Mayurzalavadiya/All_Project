package com.paymentgetway.app.data

import okhttp3.HttpUrl

object URLFactory {

    // server details
    //https://api.stripe.com/v1/customers
    private const val IS_LOCAL = true
    private const val SCHEME = "https"
    private val HOST = if (IS_LOCAL) "api.stripe.com" else "skkyn.com"
    private val API_PATH = if (IS_LOCAL) "v1/" else "websitedata/api/v2/"
//    private val PORT = if (IS_LOCAL) 8082 else 8082

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegments(API_PATH)
                .build()
    }


    // API Methods
    object Method {
        const val LOGIN = "customers"
        const val CUSTOMERS = "customers"
        const val EPHEMERAL_KEYS = "ephemeral_keys"
        const val PAYMENT_INTENTS = "payment_intents"
    }

}
