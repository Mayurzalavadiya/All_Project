package com.rapido.app.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rapido.app.core.AppPreferences
import com.rapido.app.exception.ApplicationException
import com.rapido.app.ui.manager.Navigator
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var toolbar: HasToolbar

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var appPreferences: AppPreferences

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

    fun toggleLoader(show: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).toggleLoader(show)
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
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message)
        }
    }

    fun showMessage(@StringRes stringId: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(stringId)
        }
    }

    fun showMessage(applicationException: ApplicationException) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(applicationException)
        }
    }



    open fun onBackActionPerform(): Boolean {
        return true
    }

    open fun onViewClick(view: View) {

    }

    public fun onError(throwable: Throwable) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).onError(throwable)
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
