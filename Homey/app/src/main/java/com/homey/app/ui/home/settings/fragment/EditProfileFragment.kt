package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentEditProfileBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import com.homey.app.utils.numberFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextFirstName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_first_name))
                    .matchPatter(getString(R.string.validation_p_l_p_l))
                    .errorMessage(getString(R.string.validation_please_enter_valid_first_name))
                    .check()
                validator.submit(binding.edittextLastName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_last_name))
                    .matchPatter(getString(R.string.validation_p_l_p_l))
                    .errorMessage(getString(R.string.validation_please_enter_valid_last_name))
                    .check()

                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_phone_number))
                    .check()

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
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
    ): FragmentEditProfileBinding {
        return FragmentEditProfileBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setData()
        setClickListener()
    }

    private fun setData() = with(binding) {
        arguments?.let {
            edittextFirstName.setText(it.getString(Keys.FIRST_NAME).toString().split(" ")[0])
            edittextLastName.setText(it.getString(Keys.FIRST_NAME).toString().split(" ")[1])
            edittextEmail.setText(it.getString(Keys.EMAIL).toString())
            countrycode.setCountryForPhoneCode(
                it.getString(Keys.PHONE_NUMBER).toString().split("-")[0].toInt()
            )
            val number = it.getString(Keys.PHONE_NUMBER).toString().split("-") as ArrayList
            number.removeAt(0)
            edittextPhoneNumber.setText(number.joinToString("-"))
        }
    }

    private fun setClickListener() = with(binding) {
        numberFormat(edittextPhoneNumber)
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        buttonUpdate.setOnClickListener {
            if (isValid) {
                showMessage(getString(R.string.validation_update_successfully))
                requireActivity().finish()
            }
        }

    }
}