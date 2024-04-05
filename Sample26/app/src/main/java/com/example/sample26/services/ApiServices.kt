package com.example.sample26.services

import com.example.sample26.pojo.request.UserLoginRequest
import com.example.sample26.pojo.request.UserRegisterRequest
import com.example.sample26.pojo.response.UserLoginResponseSuccessFull
import com.example.sample26.pojo.response.UserRegisterResponseSuccessFull
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    //User Login
    @POST("api/login")
    suspend fun loginUser(
        @Body userLoginRequest: UserLoginRequest
    ): UserLoginResponseSuccessFull?

    //User Register
    @POST("api/register")
    suspend fun registerUser(
        @Body userRegisterRequest: UserRegisterRequest
    ): UserRegisterResponseSuccessFull?
}