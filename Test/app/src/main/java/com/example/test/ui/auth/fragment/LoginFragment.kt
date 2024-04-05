package com.example.test.ui.auth.fragment

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.test.R
import com.example.test.databinding.FragmentLoginBinding
import com.example.test.ui.activity.TravelActivity
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.base.Const

class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    private var hideShow: Boolean = false

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageViewEye.setOnClickListener {
            if (hideShow) {
                hideShow = false
                imageViewEye.setImageResource(R.drawable.ic_close_eye)
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                hideShow = true
                imageViewEye.setImageResource(R.drawable.ic_eye)
                editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
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
                    requireContext().getString(R.string.validation_please_enter_an_email_address)
                )
                editTextEmail.requestFocus()
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_enter_a_valid_email_address)
                )
                editTextEmail.requestFocus()
            }
            pass.isEmpty() -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_enter_a_password)
                )
                editTextPassword.requestFocus()
            }
            pass.length < 6 -> {
                showMessage(
                    requireContext().getString(R.string.validation_password_length_must_be_6_digits)
                )
                editTextPassword.requestFocus()
            }
            else -> {
                myPref.saveValue(Const.IS_LOGIN, true)
                moveToNextScreen(TravelActivity::class.java,true)

            }
        }
    }


}