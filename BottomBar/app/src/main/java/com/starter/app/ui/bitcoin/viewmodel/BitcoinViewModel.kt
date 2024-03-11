package com.starter.app.ui.bitcoin.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starter.app.data.pojo.response.BitCoinResponse
import com.starter.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitcoinViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val bitCoinLiveData = MutableLiveData<BitCoinResponse>()



    fun bitCoinList() {

            viewModelScope.launch {
                val result = userRepository.getBitcoin()
                bitCoinLiveData.value = result
            }


    }

}