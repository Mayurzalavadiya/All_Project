package com.starter.app.ui.setGoal.viewmodel

import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.response.GoalResponse
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import com.starter.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val goalLiveData = APILiveData<GoalResponse>()

    fun goal() {
        viewModelScope.launch {
            val result = userRepository.goal()
            goalLiveData.value = result
        }
    }
}