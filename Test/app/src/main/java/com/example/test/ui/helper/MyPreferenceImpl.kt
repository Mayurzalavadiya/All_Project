package com.example.test.ui.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.test.ui.base.BaseActivity

class MyPreferenceImpl(private val activity: BaseActivity) : MyPreference {

    private val sharedPreferences: SharedPreferences by lazy {
        activity.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    override fun saveValue(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    override fun saveValue(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    override fun saveValue(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    override  fun saveValue(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override  fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override  fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    override  fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0F)
    }

    override  fun getInt(key: String): Int {
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

    override fun clearAllKey() {
        sharedPreferences.edit().clear().apply()
    }

}