package com.starter.app.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginVerifyOtpRequest
import com.starter.app.data.pojo.request.SendOtpRequest
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import com.starter.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    //Sign Up
    val signUpLiveData = APILiveData<Any>()

    fun signUp(request: SendOtpRequest) =
        viewModelScope.launch {
            val result = userRepository.sendOtp(request)
            signUpLiveData.value = result
        }

    //Otp
    val sendOtpLiveData = APILiveData<Any>()

    fun sendOtp(request: SendOtpRequest) =
        viewModelScope.launch {
            val result = userRepository.loginSendOtp(request)
            sendOtpLiveData.value = result
        }

    //Verify

    val verifyOtpLiveData = APILiveData<User>()

    fun verifyOtp(request: LoginVerifyOtpRequest) =
        viewModelScope.launch {
            verifyOtpLiveData.value = userRepository.loginVerifyOtp(request)
        }
}