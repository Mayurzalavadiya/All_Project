package com.homey.app.ui.auth.fragment

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.homey.app.R
import com.homey.app.databinding.AuthFragmentSignupBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.fragment.TermsConditionsFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import com.homey.app.utils.numberFormat
import com.homey.app.utils.setPasswordToggle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {


    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {

                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_phone_number))
                    .check()

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()



                validator.submit(binding.edittextPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_password))
                    .checkMinDigits(6).errorMessage(R.string.validation_please_enter_min_6_digit_password)
                    .checkMaxDigits(8).errorMessage(R.string.validation_please_enter_max_8_digit_password)
                    .check()
                validator.submit(binding.edittextConfirmPassword)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_confirm_password))
                    .matchString(binding.edittextPassword.text.toString())
                    .errorMessage(getString(R.string.validation_password_mitchmatch))
                    .check()


                if (!binding.checkBoxTerms.isChecked) {
                    throw ApplicationException(getString(R.string.validation_please_select_terms))
                }

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


    override fun bindData() {
//        setTermsCondition()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        imageviewBack.setOnClickListener { navigator.goBack() }

        numberFormat(edittextPhoneNumber)

        setPasswordToggle(edittextPassword, imageviewShowPassword)
        setPasswordToggle(edittextConfirmPassword, imageviewShowConfirePassword)

        buttonSignup.setOnClickListener {
            if (isValid) {
                navigator.load(PhoneVerificationFragment::class.java)
                    .setBundle(Bundle().apply {
                        putString(
                            Keys.COUNTRY_CODE,
                            countryCode.selectedCountryCode
                        )
                        putString(
                            Keys.PHONE_NUMBER,
                            edittextPhoneNumber.text.toString().trim()
                        )
                        putString(
                            Keys.SIGN_UP,
                            Keys.SIGN_UP
                        )
                    }).replace(true,  Keys.FRAGMENT_TAG)
            }
        }
    }

    private fun setTermsCondition() = with(binding) {
        val ss =
            SpannableString(getString(R.string.textview_by_continuing_you_agree_to_the_terms_of_service_and_privacy_policy))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navigator.load(TermsConditionsFragment::class.java).setBundle(Bundle().apply {
                    putString(Keys.TITLE, getString(R.string.textview_terms_amp_conditions))
                }).replace(true)

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.PrimaryBlack)
            }
        }

        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navigator.load(TermsConditionsFragment::class.java).setBundle(Bundle().apply {
                    putString(Keys.TITLE, getString(R.string.textview_privacy_policy))
                }).replace(true)
            }

            @RequiresApi(Build.VERSION_CODES.Q)
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.PrimaryBlack)
            }
        }
        ss.setSpan(clickableSpan, 31, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(clickableSpan1, 52, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textviewTerms.text = ss
        textviewTerms.movementMethod = LinkMovementMethod.getInstance()
    }



}
