package com.rapido.app.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.rapido.app.data.pojo.User
import com.rapido.app.data.pojo.request.SignUpRequest
import com.rapido.app.data.pojo.request.VerifyOtpRequest
import com.rapido.app.data.repository.UserRepository
import com.rapido.app.ui.base.APILiveData
import com.rapido.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    val signUpLiveData = APILiveData<User>()

    fun signUp(request: SignUpRequest) =
        viewModelScope.launch {
            val result = userRepository.signUp(request)
            signUpLiveData.value = result

        }


    val getOtpLiveData = APILiveData<Any>()

    fun getOtp(request: VerifyOtpRequest) =
        viewModelScope.launch {
            val result = userRepository.getOtp(request)
            getOtpLiveData.value = result

        }
}