package com.asis.Activity

import android.content.Context
import android.content.Intent
import android.widget.Toast

object CommonClass  {

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun moveToNextScreen(context: Context, nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }

    const val MOBILE_NUMBER = "mobileNumber"
    const val PASS = "password"
}