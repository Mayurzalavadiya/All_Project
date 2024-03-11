package com.example.test.ui.helper

interface MyPreference {
    fun saveValue(key: String, value: Int)

    fun saveValue(key: String, value: String)

    fun saveValue(key: String, value: Boolean)

    fun saveValue(key: String, value: Float)

    fun getBoolean(key: String): Boolean

    fun getString(key: String): String?

    fun getFloat(key: String): Float

    fun getInt(key: String): Int

    fun containKey(key: String): Boolean

    fun removeKey(key: String)

    fun clearAllKey()
}