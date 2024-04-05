package com.starter.app.data.repository

import com.starter.app.data.pojo.DataWrapper
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.pojo.request.LoginVerifyOtpRequest
import com.starter.app.data.pojo.request.SendOtpRequest
import com.starter.app.data.pojo.request.UpdateAddressRequest
import com.starter.app.data.pojo.request.UpdateProfileRequest
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.data.pojo.response.GoalResponse
import com.starter.app.data.pojo.response.ReadingResponse
import com.starter.app.data.pojo.response.UserResponse

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun login(request: LoginRequest): DataWrapper<User>

    suspend fun sendOtp(request: SendOtpRequest): DataWrapper<Any>
    suspend fun loginSendOtp(request: SendOtpRequest): DataWrapper<Any>
    suspend fun loginVerifyOtp(request: LoginVerifyOtpRequest): DataWrapper<User>
    suspend fun getPatientDetails(): DataWrapper<User>
    suspend fun updateProfile(request: UpdateProfileRequest): DataWrapper<User>
    suspend fun updateAddress(request: UpdateAddressRequest): DataWrapper<User>
    suspend fun logout(): DataWrapper<Any>
    suspend fun goal(): DataWrapper<GoalResponse>
    suspend fun reading(): DataWrapper<ReadingResponse>
    suspend fun updateReadingGoal(request: UpdateReadingGoalRequest): DataWrapper<Any>

}