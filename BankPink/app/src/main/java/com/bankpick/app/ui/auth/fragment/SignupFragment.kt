package com.bankpick.app.ui.auth.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bankpick.app.R
import com.bankpick.app.databinding.AuthFragmentSignupBinding
import com.bankpick.app.exception.ApplicationException
import com.bankpick.app.ui.base.BaseFragment
import com.bankpick.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Base Fragment has T type class, pass viewbinding name on this T type,
 */
@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {


    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextFullName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_full_name))
                    .check()

                validator.submit(binding.emailPasswordLayout.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()

                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_phone_number))
                    .checkMaxDigits(10)
                    .errorMessage(getString(R.string.validation_please_enter_valid_phone_number))
                    .check()

                validator.submit(binding.emailPasswordLayout.edittextPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_password))
                    .checkMinDigits(6).errorMessage("Please enter min 6 digit password")
                    .checkMaxDigits(8).errorMessage("Please enter max 8 digit password")
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    /**
     * This method is call when on onViewCreated call from life cycle
     * THis one is used for bind data to control
     */
    override fun bindData() {
        setTermsCondition()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonSignUp.setOnClickListener {
            if (isValid) {
            }
        }
    }

    //I agree to Terms & Condition
    private fun setTermsCondition() = with(binding) {

        val ss = SpannableString(getString(R.string.textview_already_have_an_account_sign_up))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navigator.goBack()

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = ContextCompat.getColor(requireActivity(), R.color.Blue)
            }
        }
        ss.setSpan(clickableSpan, 25, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textviewNewUser.text = ss
        textviewNewUser.movementMethod = LinkMovementMethod.getInstance()

    }
}