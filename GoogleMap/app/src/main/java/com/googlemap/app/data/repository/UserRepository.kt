package com.googlemap.app.data.repository

import com.googlemap.app.data.pojo.DataWrapper
import com.googlemap.app.data.pojo.User
import com.googlemap.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}