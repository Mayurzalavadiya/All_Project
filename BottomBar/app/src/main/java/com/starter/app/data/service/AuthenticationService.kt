package com.starter.app.data.service

import com.starter.app.data.URLFactory
import com.starter.app.data.pojo.ResponseBody
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.pojo.response.BitCoinResponse
import com.starter.app.data.pojo.response.StateListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

    @POST(URLFactory.Method.GET_STATE)
    suspend fun getState(): ResponseBody<StateListResponse>

}