package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentContactUsBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Validator
import com.homey.app.utils.setPasswordToggle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>() {


    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {

                validator.submit(binding.edittextSubject)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_your_subject))
                    .check()

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()

                validator.submit(binding.edittextDescription)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_your_description))
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
    ): FragmentContactUsBinding {
        return FragmentContactUsBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding){
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        buttonSubmit.setOnClickListener{
            if(isValid){
                showMessage(getString(R.string.validation_update_successfully))
                navigator.goBack()
            }
        }
    }
}