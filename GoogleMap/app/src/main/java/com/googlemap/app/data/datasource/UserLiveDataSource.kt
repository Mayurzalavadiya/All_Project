package com.googlemap.app.data.datasource

import com.googlemap.app.data.pojo.DataWrapper
import com.googlemap.app.data.pojo.User
import com.googlemap.app.data.pojo.request.LoginRequest
import com.googlemap.app.data.repository.UserRepository
import com.googlemap.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

}
