package com.homey.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentResetPasswordBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextNewPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_new_password))
                    .checkMinDigits(6).errorMessage(R.string.validation_please_enter_min_6_digit_password)
                    .checkMaxDigits(8).errorMessage(getString(R.string.validation_please_enter_max_8_digit_password))
                    .check()
                validator.submit(binding.edittextConfirmPassword)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_confirm_password))
                    .matchString(binding.edittextNewPassword.text.toString())
                    .errorMessage(getString(R.string.validation_password_mitchmatch))
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
    ): FragmentResetPasswordBinding {
        return FragmentResetPasswordBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
        buttonUpdate.setOnClickListener {
            if (isValid) {
                navigator.load(LoginFragment::class.java)
                    .clearHistory(PhoneVerificationFragment::class.java.simpleName)
                    .clearHistory(ForgotPasswordFragment::class.java.simpleName).replace(false)
            }
        }
    }

}