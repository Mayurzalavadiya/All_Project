package com.mvvm.ui.demoDagger.activity

import android.content.Context
import android.util.Log
import android.widget.Toast
import javax.inject.Inject

class ToastImpl @Inject constructor() {

    fun intent(context: Context, s: String) {
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}