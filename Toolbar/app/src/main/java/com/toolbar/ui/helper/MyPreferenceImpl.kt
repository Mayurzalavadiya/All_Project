package com.toolbar.ui.helper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.example.sample26.ui.base.BaseActivity

class MyPreferenceImpl(private val activity: BaseActivity) : MyPreference {

    private val sharedPreferences: SharedPreferences by lazy {
        activity.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    override fun savePrefsVal(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    override fun savePrefsVal(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    override fun savePrefsVal(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    override  fun savePrefsVal(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override  fun readBooleanPrefsVal(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override  fun readStringPrefVal(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    override  fun readFloatPrefVal(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0F)
    }

    override  fun readIntPrefVal(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun containKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun removeKey(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun removeAllKey() {
        sharedPreferences.edit().clear().apply()
    }

}