package com.starter.app.ui.apicalldemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.response.StateListResponse
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetStateViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val stateListLiveData = APILiveData<StateListResponse>()

    fun stateList() = viewModelScope.launch {
        val result = userRepository.getState()
        stateListLiveData.value = result
    }
}