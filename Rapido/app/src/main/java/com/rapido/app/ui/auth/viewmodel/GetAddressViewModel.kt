package com.rapido.app.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.response.AddressResponse
import com.rapido.app.data.repository.UserRepository
import com.rapido.app.ui.base.APILiveData
import com.rapido.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAddressViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    val getAddressLiveData = APILiveData<List<AddressResponse>>()

    fun getAddress() =
        viewModelScope.launch {
            val result = userRepository.getAddress()
            getAddressLiveData.value = result
        }

    val updateAddressLiveData = APILiveData<Any>()

    fun updateAddress(request: UpdateAddressRequest) =
        viewModelScope.launch {
            val result = userRepository.updateAddress(request)
            updateAddressLiveData.value = result
        }
}
