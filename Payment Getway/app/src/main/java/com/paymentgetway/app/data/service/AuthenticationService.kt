package com.paymentgetway.app.data.service

import com.paymentgetway.app.data.URLFactory
import com.paymentgetway.app.data.pojo.ResponseBody
import com.paymentgetway.app.data.pojo.User
import com.paymentgetway.app.data.pojo.request.LoginRequest
import com.paymentgetway.app.data.pojo.response.GetCustomer
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>


}