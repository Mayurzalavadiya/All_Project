package com.homehaven.app.data.repository

import com.homehaven.app.data.pojo.DataWrapper
import com.homehaven.app.data.pojo.User
import com.homehaven.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}