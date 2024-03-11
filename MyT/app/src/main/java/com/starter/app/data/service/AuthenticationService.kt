package com.starter.app.data.service

import com.starter.app.data.URLFactory
import com.starter.app.data.pojo.ResponseBody
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.pojo.request.LoginVerifyOtpRequest
import com.starter.app.data.pojo.request.SendOtpRequest
import com.starter.app.data.pojo.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

    @POST(URLFactory.Method.SEND_OTP_SIGNUP)
    suspend fun sendOtp(@Body request: SendOtpRequest): ResponseBody<Any>

    @POST(URLFactory.Method.LOGIN_SEND_OTP)
    suspend fun loginSendOtp(@Body request: SendOtpRequest): ResponseBody<Any>

    @POST(URLFactory.Method.LOGIN_VERIFY_OTP)
    suspend fun loginVerifyOtp(@Body request: LoginVerifyOtpRequest): ResponseBody<User>

}