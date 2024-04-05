package com.example.project3

import android.content.Context
import android.content.Intent
import android.widget.Toast

object CommonMethod  {

        fun showMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun moveToNextScreen(context: Context, nextClass: Class<*>) {
            val intent = Intent(context, nextClass)
            context.startActivity(intent)
        }

    const val EMAIL = "email"
    const val PASS = "password"
}