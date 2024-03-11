package com.tinder.app.data.service

import com.tinder.app.data.URLFactory
import com.tinder.app.data.pojo.ResponseBody
import com.tinder.app.data.pojo.User
import com.tinder.app.data.pojo.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

}