package com.mvvm.ui.demoDagger.activity

import android.content.Context
import javax.inject.Inject

class ToastGenerate @Inject constructor(private val toast:ToastImpl) {

    fun toast(context: Context, s: String) {
        toast.intent(context,s)
    }
}