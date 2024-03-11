package com.tinder.app.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.tinder.app.data.pojo.User
import com.tinder.app.data.pojo.request.LoginRequest
import com.tinder.app.data.repository.UserRepository
import com.tinder.app.ui.base.APILiveData
import com.tinder.app.ui.base.BaseViewModel
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