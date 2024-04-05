package com.social.app.data.repository

import com.social.app.data.pojo.DataWrapper
import com.social.app.data.pojo.User
import com.social.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}