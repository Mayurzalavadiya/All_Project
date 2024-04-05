package com.rangeseekbar.app.data.repository

import com.rangeseekbar.app.data.pojo.DataWrapper
import com.rangeseekbar.app.data.pojo.User
import com.rangeseekbar.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}