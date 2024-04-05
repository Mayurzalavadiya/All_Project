package com.daggerhilt.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.daggerhilt.R
import com.daggerhilt.ui.helper.MyPreference
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var myPref: MyPreference

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

    fun showKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyBoard() {

        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun showShackBarMessage(view: View, message: String) {
        showSnackBar(view, message)

    }

    private fun showSnackBar(view: View, message: String) {
        hideKeyBoard()
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.duration = 3000
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.setAction("OK") { snackBar.dismiss() }
        val snackView = snackBar.view
        val textView =
            snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.maxLines = 4
        textView.setTextColor( getColor(R.color.colorAccent))
        snackView.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        snackBar.show()
    }


//    @SuppressLint("SimpleDateFormat")
//    fun currentDateTime(format: String): String {     //"yyyy/MMM/dd GGG hh:mm a" format
//        val time = Calendar.getInstance().time
//        val formatter = SimpleDateFormat(format)
//        return formatter.format(time)
//    }

//
//    val scopeExceptionHandling: CoroutineExceptionHandler by lazy {
//        CoroutineExceptionHandler { _, exception ->
//            Const.hideProgressDialog()
//            when (exception) {
//                is HttpException -> {
//                    when (exception.code()) {
//                        404 -> {
//                            val jsonObject = JSONObject(
//                                exception.response()?.errorBody()!!.charStream().readText()
//                            ).getString("message")
//                            showMessage(jsonObject)
//                        }
//                    }
//                }
//
//                is InterruptedIOException ->
//                    showMessage(exception.message.toString())
//
//                else -> {
//                    showMessage(exception.message.toString())
//
//                }
//            }
//        }
//    }
}