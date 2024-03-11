package com.homey.app.data.service

import com.homey.app.data.URLFactory
import com.homey.app.data.pojo.ResponseBody
import com.homey.app.data.pojo.User
import com.homey.app.data.pojo.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

}