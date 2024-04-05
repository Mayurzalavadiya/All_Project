package com.homehaven.app.data.datasource

import com.homehaven.app.data.pojo.DataWrapper
import com.homehaven.app.data.pojo.User
import com.homehaven.app.data.pojo.request.LoginRequest
import com.homehaven.app.data.repository.UserRepository
import com.homehaven.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

}
