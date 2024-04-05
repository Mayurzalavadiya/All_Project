package com.bankpick.app.data.service

import com.bankpick.app.data.URLFactory
import com.bankpick.app.data.pojo.ResponseBody
import com.bankpick.app.data.pojo.User
import com.bankpick.app.data.pojo.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOGIN)
    suspend fun login(@Body request: LoginRequest): ResponseBody<User>

}