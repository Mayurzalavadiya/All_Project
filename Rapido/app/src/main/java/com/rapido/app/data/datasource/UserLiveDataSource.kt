package com.rapido.app.data.datasource

import com.rapido.app.data.pojo.DataWrapper
import com.rapido.app.data.pojo.User
import com.rapido.app.data.pojo.request.LoginRequest
import com.rapido.app.data.pojo.request.SignUpRequest
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.request.VerifyOtpRequest
import com.rapido.app.data.pojo.response.AddressResponse
import com.rapido.app.data.repository.UserRepository
import com.rapido.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

    override suspend fun signUp(request: SignUpRequest): DataWrapper<User> {
       return execute { authenticationService.signUp(request) }
    }

    override suspend fun getOtp(request: VerifyOtpRequest): DataWrapper<Any> {
        return execute { authenticationService.getOtp(request) }
    }

    override suspend fun getAddress(): DataWrapper<List<AddressResponse>> {
        return execute { authenticationService.getAddress() }
    }

    override suspend fun updateAddress(request: UpdateAddressRequest): DataWrapper<Any> {
        return execute { authenticationService.updateAddress(request) }
    }

}
