package com.rangeseekbar.app.data.datasource

import com.rangeseekbar.app.data.pojo.DataWrapper
import com.rangeseekbar.app.data.pojo.User
import com.rangeseekbar.app.data.pojo.request.LoginRequest
import com.rangeseekbar.app.data.repository.UserRepository
import com.rangeseekbar.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

}
