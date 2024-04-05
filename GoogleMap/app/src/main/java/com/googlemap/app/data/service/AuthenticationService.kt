package com.googlemap.app.data.service

import com.googlemap.app.data.URLFactory
import com.googlemap.app.data.pojo.ResponseBody
import com.googlemap.app.data.pojo.User
import com.googlemap.app.data.pojo.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

}