package com.paymentgetway.app.data.service

import com.paymentgetway.app.data.URLFactory
import com.paymentgetway.app.data.pojo.response.GetCustomer
import com.paymentgetway.app.data.pojo.response.GetEphemeralKeys
import com.paymentgetway.app.data.pojo.response.GetPayment
import com.paymentgetway.app.di.DiConstants
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface StripeService {


    @Headers("Authorization:Bearer ${DiConstants.SECRET_KEY}")
    @POST(URLFactory.Method.CUSTOMERS)
    suspend fun getCustomer(): GetCustomer


    @Headers("Authorization:Bearer ${DiConstants.SECRET_KEY}", "Stripe-Version: 2023-10-16")
    @POST(URLFactory.Method.EPHEMERAL_KEYS)
    suspend fun getEphemeralKeys(@Query("customer") customer: String): GetEphemeralKeys

    @Headers("Authorization:Bearer ${DiConstants.SECRET_KEY}")
    @POST(URLFactory.Method.PAYMENT_INTENTS)
    suspend fun getPaymentIntents(
        @Query("customer") customer: String,
        @Query("amount") amount: Int = 1000,
        @Query("currency") currency: String = "inr",
    ): GetPayment
}