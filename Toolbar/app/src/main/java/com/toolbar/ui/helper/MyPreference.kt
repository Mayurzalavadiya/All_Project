package com.toolbar.ui.helper

interface MyPreference {
    fun savePrefsVal(key: String, value: Int)

    fun savePrefsVal(key: String, value: String)

    fun savePrefsVal(key: String, value: Boolean)

    fun savePrefsVal(key: String, value: Float)

    fun readBooleanPrefsVal(key: String): Boolean

    fun readStringPrefVal(key: String): String?

    fun readFloatPrefVal(key: String): Float

    fun readIntPrefVal(key: String): Int

    fun containKey(key: String): Boolean

    fun removeKey(key: String)

    fun removeAllKey()
}