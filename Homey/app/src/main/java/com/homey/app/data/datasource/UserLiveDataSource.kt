package com.homey.app.data.datasource

import com.homey.app.data.pojo.DataWrapper
import com.homey.app.data.pojo.User
import com.homey.app.data.pojo.request.LoginRequest
import com.homey.app.data.repository.UserRepository
import com.homey.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

}
