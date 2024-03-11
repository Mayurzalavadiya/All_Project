package com.daggerhilt.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.daggerhilt.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.InterruptedIOException

abstract class BaseFragment<T : ViewBinding> : Fragment() {


    private var _binding: T? = null

    protected val binding: T
        get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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

    val myPref get() = (activity as BaseActivity).myPref

    protected  fun hideKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideKeyBoard()
        }
    }

    protected fun showKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showKeyBoard()
        }
    }
    //Move To Main Thread
    protected fun runMainThread(unit: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            unit()
        }
    }

    //Show Message
    protected fun showMessage(message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message)
        }
    }

    protected fun moveToNextScreen(nextClass: Class<*>, isCloseCurrent:Boolean=false) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).moveToNextScreen(nextClass,isCloseCurrent)
        }
    }


    private fun showSnackBar(message: String) {
        hideKeyBoard()
        if (view != null) {
            val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            snackBar.duration = 3000
            snackBar.setActionTextColor(Color.WHITE)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
            val snackView = snackBar.view
            val textView =
                snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 4

            snackView.setBackgroundColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.teal
                )
            )
            snackBar.show()
        }
    }

    private fun showSnackBar(message: String, viewSet: View) {
        hideKeyBoard()
        if (viewSet != null) {
            val snackBar = Snackbar.make(viewSet, message, Snackbar.LENGTH_LONG)
            snackBar.duration = 3000
            snackBar.setActionTextColor(Color.WHITE)
            snackBar.setAction("OK", View.OnClickListener { snackBar.dismiss() })
            val snackView = snackBar.view
            val textView =
                snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 4

            snackView.setBackgroundColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.teal
                )
            )
            snackBar.show()
        }
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
    protected fun clearBackStack(){
        if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
            requireActivity().supportFragmentManager.popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

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
//                            showMessage( jsonObject) }
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


