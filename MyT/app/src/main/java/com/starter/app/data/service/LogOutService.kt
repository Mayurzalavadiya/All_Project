package com.starter.app.data.service

import com.starter.app.data.URLFactory
import com.starter.app.data.pojo.ResponseBody
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.pojo.request.LoginVerifyOtpRequest
import com.starter.app.data.pojo.request.SendOtpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LogOutService {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.LOG_OUT)
    suspend fun logOut(): ResponseBody<Any>


}