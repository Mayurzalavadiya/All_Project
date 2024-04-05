package com.example.test.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.ui.helper.MyPreference
import com.example.test.ui.helper.MyPreferenceImpl
import java.text.SimpleDateFormat
import java.util.Calendar

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var myPref: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(createView())

        myPref = MyPreferenceImpl(this)
        onBindActivity()

    }


    fun getMyPref(): MyPreference {
        return myPref
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


    abstract fun createView(): View
    abstract fun findFragmentPlaceHolder(): Int
    abstract fun onBindActivity()


    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun moveToNextScreen(nextClass: Class<*>, isCloseCurrent: Boolean = false) {
        val intent = Intent(this, nextClass)
        startActivity(intent)
        if (isCloseCurrent)
            finish()
    }

    fun showKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard() {
        // Check if no view has focus:

        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun currentDateTime(format: String): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(format)
        return formatter.format(time)
    }

}