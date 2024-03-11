package com.example.sample26.ui.base

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.io.InterruptedIOException

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val MY_PREF = ""
        const val IS_LOGIN = "IS_LOGIN"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            MY_PREF, Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(createView())

        onBindActivity()
    }

    fun loadFragment(
        fragment: BaseFragment<*>,
        isReplace: Boolean = true,
        isAllowBackStack: Boolean = true
    ) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (isReplace) {
            beginTransaction.replace(findFragmentPlaceHolder(), fragment)
        } else {
            beginTransaction.add(findFragmentPlaceHolder(), fragment)
        }

        if (isAllowBackStack)
            beginTransaction.addToBackStack(fragment.tag)

        beginTransaction.commitAllowingStateLoss()
    }



    protected fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun moveToNextScreen(context: Context, nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }

    abstract fun createView(): View
    abstract fun findFragmentPlaceHolder(): Int
    abstract fun onBindActivity()


    fun savePrefsVal(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun savePrefsVal(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun savePrefsVal(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor?.putFloat(key, value)
        editor?.apply()
    }

    protected fun savePrefsVal(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    protected fun readBooleanPrefsVal(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    protected fun readStringPrefVal(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    protected fun readBooleanPrefVal(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    protected fun readFloatPrefVal(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0F)
    }

    protected fun readIntPrefVal(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    protected fun containKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    protected fun removeKey(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    protected fun removeAllKey() {
        sharedPreferences.edit().clear().apply()
    }

}