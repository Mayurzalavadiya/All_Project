package com.example.sample26.ui.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.sample26.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.text.SimpleDateFormat
import java.util.Calendar

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding!!

    companion object {
        const val MY_PREF = ""
        const val IS_LOGIN = "IS_LOGIN"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(
            MY_PREF, Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    protected abstract fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachedToParent: Boolean
    ): T

    protected abstract fun bindData()

    fun loadFragment(
        fragment: BaseFragment<*>, isReplace: Boolean = true, isAllowBackStack: Boolean = true
    ) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).loadFragment(fragment, isReplace, isAllowBackStack)
        }
    }

    protected val scopeExceptionHandling: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, exception ->

            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        404 -> {
                            hideProgressDialog()
                            showMessage("Data Not Found")
                        }

                        400 -> {
                            hideProgressDialog()
                            showMessage("User Not Found")
                        }
                    }
                }

                is InterruptedIOException ->
                    showMessage(exception.message!!)

                else -> {
                    showMessage(exception.message.toString())

                }
            }
        }
    }


    //Move To Main Thread
    fun runMainThread(unit: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            unit()
        }
    }

    //Show Message
    protected fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun moveToNextScreen(nextClass: Class<*>) {
        val intent = Intent(requireActivity(), nextClass)
        requireActivity().startActivity(intent)
    }

    @SuppressLint("SimpleDateFormat")
    fun currentDateTime(format: String): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(format)
        return formatter.format(time)
    }

    protected fun replaceWithBackStack(fragmentContainer: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainer,
                fragment
            )
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    protected fun replace(fragmentContainer: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainer,
                fragment
            )
            .commit()
    }

    protected fun popBackStack() {
        if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    protected fun showProgressDialog(context: Context) {
        try {
            hideProgressDialog()
            val dialog = Dialog(context)
            progressDialog = dialog
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_progress_view)
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog.show()
        } catch (_: Exception) {
        }
    }

    private var progressDialog: Dialog? = null

    protected fun hideProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
            }

        } catch (_: Exception) {
        }
        progressDialog = null
    }


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


