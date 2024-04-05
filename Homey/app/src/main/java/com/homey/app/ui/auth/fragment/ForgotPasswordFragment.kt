package com.homey.app.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentForgotPasswordBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.security.Key
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {


                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_phone_number))
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
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater, container, attachToRoot)

    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonSubmit.setOnClickListener {
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
                    }).replace(true)
            }
        }

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
    }

}