package com.bankpick.app.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.bankpick.app.data.pojo.User
import com.bankpick.app.data.pojo.request.LoginRequest
import com.bankpick.app.data.repository.UserRepository
import com.bankpick.app.ui.base.APILiveData
import com.bankpick.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val loginLiveData = APILiveData<User>()

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            val result = userRepository.login(request)
            loginLiveData.value = result
        }
    }
}