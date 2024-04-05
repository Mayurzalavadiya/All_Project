package com.homey.app.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.homey.app.R
import com.homey.app.core.AppSession
import com.homey.app.exception.ApplicationException
import com.homey.app.exception.AuthenticationException
import com.homey.app.exception.ServerException
import com.homey.app.ui.manager.Navigator
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

abstract class BaseBottomSheet<T : ViewBinding> : BottomSheetDialogFragment() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var appSession: AppSession

    protected lateinit var toolbar: HasToolbar

    private var _binding: T? = null

    protected val binding: T
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container, false)
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(viewTreeObserver)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parent = view.parent as? View
        parent?.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet =
                    findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(R.drawable.bg_bottom_dialog)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        BottomSheetBehavior.from<View>(
            (dialog as BottomSheetDialog)
                .findViewById(R.id.design_bottom_sheet)!!
        )
            .addBottomSheetCallback(addBottomSheetCallback)
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

    fun getParentFragment(targetFragment: Class<T>): T? {
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

    open fun onBackActionPerform(): Boolean {
        return true
    }

    open fun onViewClick(view: View) {

    }

    override fun onDestroyView() {
        destroyViewBinding()
        requireView().viewTreeObserver.removeOnGlobalLayoutListener(viewTreeObserver)
        super.onDestroyView()
    }

    fun onError(throwable: Throwable) {
        try {
            when (throwable) {
                is ServerException -> {
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).showToast(throwable.message.toString())
                    }
                }

                is ConnectException ->
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).showToast(getString(R.string.connection_exception))
                    }

                is AuthenticationException -> {
                    if (activity is BaseActivity) {
//                        (activity as BaseActivity).logout()
                    }
                }

                is ApplicationException -> {
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).showToast(throwable.message)
                    }
                }

                is SocketTimeoutException -> if (activity is BaseActivity) {
                    (activity as BaseActivity).showToast(getString(R.string.socket_time_out_exception))
                }

                else -> {
                    if (activity is BaseActivity) {
                        (activity as BaseActivity).showToast(getString(R.string.other_exception) + throwable.message)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    fun hideSystemUI() {
//        (activity as BaseActivity).hideSystemUI()
//    }
//
//    fun showSystemUI() {
//        (activity as BaseActivity).showSystemUI()
//    }

    fun showToolbar(b: Boolean) {
        toolbar.showToolbar(b)
    }


    fun showMessage(message: String) {
        message.let {
            with(Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT)) {
                view.z = 200f
                // Used to show snackbar on top
                val params = view.layoutParams as CoordinatorLayout.LayoutParams
                params.gravity = Gravity.TOP
                view.layoutParams = params
                view.setBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimary,
                        null
                    )
                )

                val textView =
                    view.findViewById<AppCompatTextView>(R.id.snackbar_text)
                textView.maxLines = 4
                show()
            }
        }
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
    protected abstract fun destroyViewBinding()

    fun show() {
        show(parentFragmentManager, this.tag)
    }

    protected var bottomSheetBehavior = BottomSheetBehavior.STATE_HALF_EXPANDED

    // For full wrap content
    private val viewTreeObserver by lazy {
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BottomSheetBehavior.from<View>(
                    (dialog as BottomSheetDialog)
                        .findViewById(R.id.design_bottom_sheet)!!
                ).apply {
                    state = bottomSheetBehavior
                    peekHeight = 0
                }
                view!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    }

    /* private fun setBottomSheetBehavior(bottomSheetBehavior: Int) {
         this.bottomSheetBehavior = bottomSheetBehavior
     }*/

    // For bottom sheet state callback
    private val addBottomSheetCallback by lazy {
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                    this@BaseBottomSheet.dismiss()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded) {
            super.show(manager, tag)
        }
    }
}
