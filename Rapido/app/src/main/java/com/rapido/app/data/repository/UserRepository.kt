package com.rapido.app.data.repository

import com.rapido.app.data.pojo.DataWrapper
import com.rapido.app.data.pojo.User
import com.rapido.app.data.pojo.request.LoginRequest
import com.rapido.app.data.pojo.request.SignUpRequest
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.request.VerifyOtpRequest
import com.rapido.app.data.pojo.response.AddressResponse

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>
    suspend fun signUp(request: SignUpRequest): DataWrapper<User>
    suspend fun getOtp(request: VerifyOtpRequest): DataWrapper<Any>
    suspend fun getAddress(): DataWrapper<List<AddressResponse>>
    suspend fun updateAddress(request: UpdateAddressRequest): DataWrapper<Any>

}