package com.homehaven.app.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.homehaven.app.R
import com.homehaven.app.exception.ApplicationException
import com.homehaven.app.exception.AuthenticationException
import com.homehaven.app.exception.ServerException
import com.homehaven.app.ui.manager.Navigator
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var toolbar: HasToolbar

    @Inject
    lateinit var navigator: Navigator

    private var _binding: T? = null

    protected val binding: T
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (activity is HasToolbar)
            toolbar = activity as HasToolbar
    }


    fun hideKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideKeyboard()
        }
    }

    fun showKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showKeyboard()
        }
    }


    fun <T : BaseFragment<*>> getParentFragment(targetFragment: Class<T>): T? {
        if (parentFragment == null) return null
        try {
            return targetFragment.cast(parentFragment)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
        return null
    }


    open fun onShow() {

    }

    fun showMessage(message: String) {
        showSnackBar(message)

    }

    fun showMessage(@StringRes stringId: Int) {
        showSnackBar(getString(stringId))
    }

    fun showMessage(applicationException: ApplicationException) {

        showSnackBar(applicationException.message)
    }

    private fun showSnackBar(message: String) {
        hideKeyBoard()
        if (view != null) {
            val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            snackbar.duration = 3000
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.setAction("OK", View.OnClickListener { snackbar.dismiss() })
            val snackView = snackbar.view
            val textView =
                snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 4

            snackView.setBackgroundColor(requireActivity().resources.getColor(R.color.colorPrimary))
            snackbar.show()
        }
    }

    private fun showSnackBar(message: String, viewSet: View) {
        hideKeyBoard()
        if (viewSet != null) {
            val snackbar = Snackbar.make(viewSet, message, Snackbar.LENGTH_LONG)
            snackbar.duration = 3000
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.setAction("OK", View.OnClickListener { snackbar.dismiss() })
            val snackView = snackbar.getView()
            val textView =
                snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 4

            snackView.setBackgroundColor(requireActivity().resources.getColor(R.color.colorPrimary))
            snackbar.show()
        }
    }


    open fun onBackActionPerform(): Boolean {
        return true
    }

    open fun onViewClick(view: View) {

    }

    public fun onError(throwable: Throwable) {
        try {
            when (throwable) {
                is ServerException -> showMessage(throwable.message.toString())
                is ConnectException -> showMessage(R.string.connection_exception)
                is AuthenticationException -> {
                    logout()
                }
                is ApplicationException -> {
                    showMessage(throwable.toString())
                }
                is SocketTimeoutException -> showMessage(R.string.socket_time_out_exception)
                else -> showMessage(getString(R.string.other_exception) + throwable.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun logout() {
        // Write logout code
    }

    /**
     * This method is used for binding view with your binding
     */
    protected abstract fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): T

    protected abstract fun bindData()
}
