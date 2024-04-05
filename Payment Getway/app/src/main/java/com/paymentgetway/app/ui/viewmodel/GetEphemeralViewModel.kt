package com.paymentgetway.app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.paymentgetway.app.data.pojo.User
import com.paymentgetway.app.data.pojo.request.EphemeralKeysRequest
import com.paymentgetway.app.data.pojo.request.LoginRequest
import com.paymentgetway.app.data.pojo.response.GetCustomer
import com.paymentgetway.app.data.pojo.response.GetEphemeralKeys
import com.paymentgetway.app.data.repository.UserRepository
import com.paymentgetway.app.ui.base.APILiveData
import com.paymentgetway.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetEphemeralViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val ephemeralLiveData = MutableLiveData<GetEphemeralKeys>()

    fun ephemeral(request: String) {
        viewModelScope.launch {
            val result = userRepository.getEphemeralKeys(request)
            ephemeralLiveData.value = result
        }
    }
}