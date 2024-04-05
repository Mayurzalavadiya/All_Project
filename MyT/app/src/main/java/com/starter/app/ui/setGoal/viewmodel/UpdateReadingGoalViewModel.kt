package com.starter.app.ui.setGoal.viewmodel

import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.data.repository.UserRepository
import com.starter.app.ui.base.APILiveData
import com.starter.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateReadingGoalViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    val UpdateGoalLiveData = APILiveData<Any>()

    fun updateReadingGoal(request:UpdateReadingGoalRequest) {
        viewModelScope.launch {
            val result = userRepository.updateReadingGoal(request)
            UpdateGoalLiveData.value = result
        }
    }
}