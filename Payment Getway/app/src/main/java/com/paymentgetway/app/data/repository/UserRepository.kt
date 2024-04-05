package com.paymentgetway.app.data.repository

import com.paymentgetway.app.data.pojo.DataWrapper
import com.paymentgetway.app.data.pojo.User
import com.paymentgetway.app.data.pojo.request.EphemeralKeysRequest
import com.paymentgetway.app.data.pojo.request.LoginRequest
import com.paymentgetway.app.data.pojo.request.PaymentIntentRequest
import com.paymentgetway.app.data.pojo.response.GetCustomer
import com.paymentgetway.app.data.pojo.response.GetEphemeralKeys
import com.paymentgetway.app.data.pojo.response.GetPayment

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>
    suspend fun getCustomer(): GetCustomer
    suspend fun getEphemeralKeys(request: String): GetEphemeralKeys
    suspend fun getPaymentIntents(request: String): GetPayment

}