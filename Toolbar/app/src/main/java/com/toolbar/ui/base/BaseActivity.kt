package com.example.sample26.ui.base

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.toolbar.ui.helper.MyPreference
import com.toolbar.ui.helper.MyPreferenceImpl

abstract class BaseActivity : AppCompatActivity() {


    private lateinit var myPref: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(createView())

        onBindActivity()

        myPref = MyPreferenceImpl(this)
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


    protected fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun moveToNextScreen(context: Context, nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }



}