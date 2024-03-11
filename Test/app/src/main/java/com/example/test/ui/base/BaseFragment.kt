package com.example.test.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

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

    val myPref get() = (activity as BaseActivity).getMyPref()

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
    //Move To Main Thread
    fun runMainThread(unit: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            unit()
        }
    }

    //Show Message
    protected fun showMessage(message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message)
        }
//        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun moveToNextScreen(nextClass: Class<*>, isCloseCurrent:Boolean=false) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).moveToNextScreen(nextClass,isCloseCurrent)
        }
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
    fun clearBackStack(){
        if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
            requireActivity().supportFragmentManager.popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }


}


