package com.starter.app.ui.home.viewModel

import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.LoginRequest
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import com.starter.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogOutViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    val logOutLiveData = APILiveData<Any>()

    fun logOut() {
        viewModelScope.launch {
            val result = userRepository.logout()
            logOutLiveData.value = result
        }
    }
}