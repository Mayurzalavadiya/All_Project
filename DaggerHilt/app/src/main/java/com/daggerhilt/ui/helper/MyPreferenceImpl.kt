package com.daggerhilt.ui.helper

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class MyPreferenceImpl @Inject constructor(private val context: Context) : MyPreference {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    override fun setIntPrefs(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    override fun setStringPrefs(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    override fun setFloatPrefs(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    override  fun setBooleanPrefs(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override  fun getBooleanPref(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override  fun getStringPref(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    override  fun getFloatPref(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0F)
    }

    override  fun getIntPref(key: String): Int {
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