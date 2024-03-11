package com.starter.app.data.datasource

import com.starter.app.data.pojo.DataWrapper
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.pojo.response.BitCoinResponse
import com.starter.app.data.pojo.response.StateListResponse
import com.starter.app.data.repository.UserRepository
import com.starter.app.data.service.AuthenticationService
import com.starter.app.data.service.BitCoinService
import com.starter.app.di.DiConstants
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService, private val bitCoinService: BitCoinService) :
    BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

    override suspend fun getState(): DataWrapper<StateListResponse> {
        return execute { authenticationService.getState() }
    }

    override suspend fun getBitcoin(): BitCoinResponse {
        return bitCoinService.getBitcoin()
    }
}
