package com.homey.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.databinding.AuthFragmentLoginBinding
import com.homey.app.di.DiConstants
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.activity.HomeActivity
import com.homey.app.ui.auth.interfaces.ClickCurrencyListener
import com.homey.app.ui.auth.interfaces.ClickLanguageListener
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import com.homey.app.utils.numberFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {


    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var appPreferences: AppPreferences

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(R.string.validation_please_enter_phone_number)
                    .check()

                validator.submit(binding.edittextPassword)
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
        attachToRoot: Boolean,
    ): AuthFragmentLoginBinding {
        return AuthFragmentLoginBinding.inflate(inflater, container, attachToRoot)
    }


    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        numberFormat(edittextPhoneNumber)
        buttonLogin.setOnClickListener {
            if (isValid) {
                appPreferences.putBoolean(Keys.IS_LOGIN, true)
                navigator.loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
                activity?.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            }
        }

        textviewForgotPassword.setOnClickListener {
            navigator.load(ForgotPasswordFragment::class.java).replace(true)
        }

        textviewLanguage.setOnClickListener { setLanguageDialog() }
        textviewCurrency.setOnClickListener { setCurrencyDialog() }

        textviewSignUp.setOnClickListener {
            navigator.load(AccountTypeFragment::class.java).replace(true)
        }

    }

    private fun setLanguageDialog() {
        val languageBottomSheetFragment =
            LanguageBottomSheetFragment(
                LanguageListener(),
                binding.textviewLanguage.text.toString()
            )
        languageBottomSheetFragment.show(childFragmentManager, languageBottomSheetFragment.tag)
    }

    private fun setCurrencyDialog() {
        val currencyBottomSheetFragment =
            CurrencyBottomSheetFragment(
                CurrencyListener(),
                binding.textviewCurrency.text.toString()
            )
        currencyBottomSheetFragment.show(childFragmentManager, currencyBottomSheetFragment.tag)
    }


    inner class CurrencyListener : ClickCurrencyListener {
        override fun onClick(image: Int, language: String) = with(binding) {
            textviewCurrency.text = language
            imageviewCurrency.setImageResource(image)
        }


    }

    inner class LanguageListener : ClickLanguageListener {
        override fun onClick(image: String, language: String) = with(binding) {
                when (language) {
                    requireActivity().getString(R.string.textview_arabic) -> {
                        appPreferences.putString(DiConstants.LANGUAGE,"ar")
                        updateLanguage("ar")
                    }
                    requireActivity().getString(R.string.textview_english) -> {
                        appPreferences.putString(DiConstants.LANGUAGE,"en")
                        updateLanguage("en")
                    }
                }
            textviewLanguage.text = language
            textviewLang.text = image
        }

    }
}