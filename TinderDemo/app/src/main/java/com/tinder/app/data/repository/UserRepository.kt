package com.tinder.app.data.repository

import com.tinder.app.data.pojo.DataWrapper
import com.tinder.app.data.pojo.User
import com.tinder.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}