package com.starter.app.data.datasource

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
import com.starter.app.data.repository.UserRepository
import com.starter.app.data.service.AuthenticationService
import com.starter.app.data.service.LogOutService
import com.starter.app.data.service.PatientDetailServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val logOutService: LogOutService,
    private val patientDetailServices: PatientDetailServices
) :
    BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

    override suspend fun sendOtp(request: SendOtpRequest): DataWrapper<Any> {
        return execute { authenticationService.sendOtp(request) }
    }

    override suspend fun loginSendOtp(request: SendOtpRequest): DataWrapper<Any> {
        return execute { authenticationService.loginSendOtp(request) }
    }

    override suspend fun loginVerifyOtp(request: LoginVerifyOtpRequest): DataWrapper<User> {
        return execute { authenticationService.loginVerifyOtp(request) }
    }

    override suspend fun getPatientDetails(): DataWrapper<User> {
        return execute { patientDetailServices.getPatientDetails() }
    }

    override suspend fun updateProfile(request: UpdateProfileRequest): DataWrapper<User> {
        return execute { patientDetailServices.updateProfile(request) }
    }

    override suspend fun updateAddress(request: UpdateAddressRequest): DataWrapper<User> {
        return execute { patientDetailServices.updateAddress(request) }
    }

    override suspend fun logout(): DataWrapper<Any> {
        return execute { logOutService.logOut() }
    }

    override suspend fun goal(): DataWrapper<GoalResponse> {
        return execute { patientDetailServices.goal() }
    }

    override suspend fun reading(): DataWrapper<ReadingResponse> {
        return execute { patientDetailServices.reading() }
    }

    override suspend fun updateReadingGoal(request: UpdateReadingGoalRequest): DataWrapper<Any> {
        return execute { patientDetailServices.updateReadingGoal(request) }
    }
}
