package com.example.sample26.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.sample26.R
import com.example.sample26.databinding.FragmentForgotPasswordBinding
import com.example.sample26.ui.base.BaseFragment

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentForgotPasswordBinding {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)
        return  FragmentForgotPasswordBinding.inflate(layoutInflater,container,attachedToParent)
    }

    override fun bindData() {
        setClickListener()
    }


    override fun onStart() {
        super.onStart()
        setTextColor()
    }

    private fun setTextColor() = with(binding) {
        val ss =
            SpannableString(getString(R.string.back_to_log_in))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.popBackStack()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.black)
            }
        }
        ss.setSpan(clickableSpan, 8, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        ss.setSpan( StyleSpan(Typeface.BOLD), 8,14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewBackLogin.text = ss
        textViewBackLogin.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setClickListener() = with(binding) {

        buttonSubmit.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()

        if (email.isEmpty()) {
            showMessage(

                requireContext().getString(R.string.validation_please_enter_an_email_address)
            )
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(

                requireContext().getString(R.string.validation_please_enter_a_valid_email_address)
            )
            editTextEmail.requestFocus()
        }  else {
            showMessage(

                requireContext().getString(R.string.validation_forgot_password_successfully)
            )
            popBackStack()
        }
    }
}