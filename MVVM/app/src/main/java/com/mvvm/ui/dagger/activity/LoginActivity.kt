package com.mvvm.ui.dagger.activity

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.mvvm.R
import com.mvvm.databinding.ActivityLoginBinding
import com.mvvm.di.App
import com.mvvm.ui.base.BaseActivity
import com.mvvm.ui.dagger.service.UserRegistrationService
import com.mvvm.ui.helper.MyPreference
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var isPasswordShow = false

    @Inject
    lateinit var userRegistrationService: UserRegistrationService

    override fun createView(): View {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {

        val application = application
        if (application is App) {
            application.appComponent.inject(this)
        }
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageViewEye.setOnClickListener {
            if (isPasswordShow) {
                isPasswordShow = false
                imageViewEye.setImageResource(R.drawable.baseline_visibility_off_24)
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                isPasswordShow = true
                imageViewEye.setImageResource(R.drawable.baseline_visibility_24)
                editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }
        buttonLogin.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                showMessage(
                    getString(R.string.validation_please_enter_an_email_address)
                )
                editTextEmail.requestFocus()
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showMessage(
                    getString(R.string.validation_please_enter_a_valid_email_address)
                )
                editTextEmail.requestFocus()
            }

            pass.isEmpty() -> {
                showMessage(
                    getString(R.string.validation_please_enter_a_password)
                )
                editTextPassword.requestFocus()
            }

            pass.length < 6 -> {
                showMessage(
                    getString(R.string.validation_password_length_must_be_6_digits)
                )
                editTextPassword.requestFocus()
            }

            else -> {


                userRegistrationService.registration(email, pass)
            }
        }
    }


}