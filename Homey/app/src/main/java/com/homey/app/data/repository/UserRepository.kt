package com.homey.app.data.repository

import com.homey.app.data.pojo.DataWrapper
import com.homey.app.data.pojo.User
import com.homey.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}