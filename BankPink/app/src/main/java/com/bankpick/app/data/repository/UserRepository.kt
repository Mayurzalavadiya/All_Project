package com.bankpick.app.data.repository

import com.bankpick.app.data.pojo.DataWrapper
import com.bankpick.app.data.pojo.User
import com.bankpick.app.data.pojo.request.LoginRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

}