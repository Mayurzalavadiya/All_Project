package com.mvvm.ui.helper


interface MyPreference {

    fun setIntPrefs(key: String, value: Int)

    fun setStringPrefs(key: String, value: String)

    fun setBooleanPrefs(key: String, value: Boolean)

    fun setFloatPrefs(key: String, value: Float)

    fun getBooleanPref(key: String): Boolean

    fun getStringPref(key: String): String?

    fun getFloatPref(key: String): Float

    fun getIntPref(key: String): Int

    fun containKey(key: String): Boolean

    fun removeKey(key: String)

    fun removeAllKey()
}