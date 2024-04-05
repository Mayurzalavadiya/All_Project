package com.daggerhilt.ui.counter.viewModel

import androidx.lifecycle.ViewModel
import com.daggerhilt.ui.counter.activity.MyCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(private val myCounter: MyCounter) : ViewModel() {

    var count = myCounter.count
}