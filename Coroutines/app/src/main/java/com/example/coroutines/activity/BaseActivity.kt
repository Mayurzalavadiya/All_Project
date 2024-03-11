package com.example.coroutines.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val MY_PREF = ""
        const val IS_LOGIN = "IS_LOGIN"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            MY_PREF,
            Context.MODE_PRIVATE
        )
    }


    protected fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun moveToNextScreen(context: Context, nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }

    protected fun savePrefsVal(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    protected fun readBooleanPrefsVal(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

}