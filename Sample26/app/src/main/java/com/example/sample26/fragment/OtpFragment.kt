package com.example.sample26.fragment

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.sample26.Const
import com.example.sample26.activity.HomeActivity
import com.example.sample26.R
import com.example.sample26.databinding.FragmentOtpBinding
import com.example.sample26.ui.base.BaseFragment

//
class OtpFragment : BaseFragment<FragmentOtpBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
//            Handler(Looper.getMainLooper()).postDelayed({
            requireArguments().getString(Const.TOKEN)
                ?.let { showMessage(it) }
//            },100)
        }
    }


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentOtpBinding {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.blue)
        return FragmentOtpBinding.inflate(layoutInflater, container, false)
    }

    override fun bindData() {
        setClickListener()
        onTextChangeListener()
    }


    private fun onTextChangeListener() = with(binding) {

        changeListener(editTextOtp1, editTextOtp2)
        changeListener(editTextOtp2, editTextOtp3)
        changeListener(editTextOtp3, editTextOtp4)

    }

    private fun changeListener(
        editTextFirst: AppCompatEditText,
        editTextSecond: AppCompatEditText
    ) {
        editTextFirst.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                val textLength1: Int = editTextFirst.text.toString().trim().length
                if (textLength1 >= 1) {
                    editTextSecond.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }
        })
        editTextSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty()) {
                    editTextFirst.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }
        })
    }


    private fun setClickListener() = with(binding) {
        buttonVerify.setOnClickListener {
            checkValidation()

        }
    }

    override fun onStart() {
        super.onStart()
        setTextColor()

    }

    private fun setTextColor() = with(binding) {
        val ss = SpannableString(getString(R.string.expired_in_00_22))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 11, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewExpire.text = ss
        textViewExpire.movementMethod = LinkMovementMethod.getInstance()


        val ss1 = SpannableString(getString(R.string.resend_code))
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                showMessage(
                     "Resend OTP on your email address"
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.black)
            }
        }
        ss1.setSpan(clickableSpan1, 7, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textViewResendCode.text = ss1
        textViewResendCode.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun checkValidation() = with(binding) {

        if (editTextOtp1.text.toString().trim().isEmpty()) {
            showMessage(
                               requireContext().getString(R.string.validation_please_enter_proper_otp)
            )
            editTextOtp1.requestFocus()
        } else if (editTextOtp2.text.toString().trim().isEmpty()) {
            showMessage(
                             requireContext().getString(R.string.validation_please_enter_proper_otp)
            )
            editTextOtp2.requestFocus()
        } else if (editTextOtp3.text.toString().trim().isEmpty()) {
            showMessage(
                    requireContext().getString(R.string.validation_please_enter_proper_otp)
            )
            editTextOtp3.requestFocus()
        } else if (editTextOtp4.text.toString().trim().isEmpty()) {
            showMessage(
                          requireContext().getString(R.string.validation_please_enter_proper_otp)
            )
            editTextOtp4.requestFocus()
        } else {
            moveToNextScreen(HomeActivity::class.java)
            savePrefsVal(IS_LOGIN,true)
            requireActivity().finish()
        }
    }
}