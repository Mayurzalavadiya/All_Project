package com.starter.app.ui.navbar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.response.StateListResponse
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavMenuViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val navLiveData = APILiveData<StateListResponse>()

    fun getMenuList() = viewModelScope.launch {
        navLiveData.value = userRepository.getState()
    }
}