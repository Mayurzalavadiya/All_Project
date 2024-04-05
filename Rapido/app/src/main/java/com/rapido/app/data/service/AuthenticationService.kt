package com.rapido.app.data.service

import com.rapido.app.data.URLFactory
import com.rapido.app.data.pojo.ResponseBody
import com.rapido.app.data.pojo.User
import com.rapido.app.data.pojo.request.LoginRequest
import com.rapido.app.data.pojo.request.SignUpRequest
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.request.VerifyOtpRequest
import com.rapido.app.data.pojo.response.AddressResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

    @POST(URLFactory.Method.SIGN_UP)
    suspend fun signUp(@Body request: SignUpRequest): ResponseBody<User>

    @POST(URLFactory.Method.GET_OTP)
    suspend fun getOtp(@Body request: VerifyOtpRequest): ResponseBody<Any>

    @POST(URLFactory.Method.GET_ADDRESS)
    suspend fun getAddress(): ResponseBody<List<AddressResponse>>

    @POST(URLFactory.Method.UPDATE_ADDRESS)
    suspend fun updateAddress(@Body request: UpdateAddressRequest): ResponseBody<Any>

}