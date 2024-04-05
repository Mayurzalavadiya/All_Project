package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentChangePasswordBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Validator
import com.homey.app.utils.setPasswordToggle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {

                validator.submit(binding.edittextCurrentPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_current_password))
                    .check()
                validator.submit(binding.edittextNewPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_password))
                    .checkMinDigits(6).errorMessage(getString(R.string.validation_please_enter_min_6_digit_password))
                    .checkMaxDigits(8).errorMessage(R.string.validation_please_enter_max_8_digit_password)
                    .notMatchString(binding.edittextCurrentPassword.text.toString()).errorMessage(
                        getString(
                            R.string.validation_password_same_as_old_password
                        ))
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
    ): FragmentChangePasswordBinding {
        return FragmentChangePasswordBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setClickListener()
    }

    private fun setClickListener() = with(binding){
        setPasswordToggle(edittextCurrentPassword, imageviewShowCurrentPassword)
        setPasswordToggle(edittextNewPassword, imageviewShowNewPassword)
        setPasswordToggle(edittextConfirmPassword, imageviewShowConfirePassword)

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        buttonUpdate.setOnClickListener{
            if(isValid){
                showMessage(getString(R.string.validation_update_successfully))
                navigator.goBack()
            }
        }
    }

}