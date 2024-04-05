package com.starter.app.ui

import android.content.Context
import android.widget.Toast

object Const {
    const val DATA = "data"
    const val PRODUCT = "product"
    const val POSITION = "position"
    const val IMAGE_CODE = 1
    const val STORAGE_CODE = 1000

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}