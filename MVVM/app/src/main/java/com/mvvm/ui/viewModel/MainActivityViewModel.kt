package com.mvvm.ui.viewModel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() { 
    
    var number = 0
      
    fun addOne() { 
        number++ 
    }

}