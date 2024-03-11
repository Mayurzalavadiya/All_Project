package com.asis.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asis.Activity.CommonClass
import com.asis.R
import com.asis.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private var hideShowPass: Boolean = false
    private var hideShowConfirmPass: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        imageViewBackArrow.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        buttonUpdate.setOnClickListener {
            checkValidation()
        }

        imageViewEye.setOnClickListener {
            if (hideShowPass) {
                hideShowPass = false
                imageViewEye.setImageResource(R.drawable.ic_hide)
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                hideShowPass = true
                imageViewEye.setImageResource(R.drawable.ic_eye)
                editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        imageViewConfirmEye.setOnClickListener {
            if (hideShowConfirmPass) {
                hideShowConfirmPass = false
                imageViewConfirmEye.setImageResource(R.drawable.ic_hide)
                editTextConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                hideShowConfirmPass = true
                imageViewConfirmEye.setImageResource(R.drawable.ic_eye)
                editTextConfirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }
    }

    private fun checkValidation() = with(binding) {
        val pass = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirmPassword.text.toString().trim()

        if (pass.isEmpty()) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_password)
            )
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_password_length_must_be_6_digits)
            )
            editTextPassword.requestFocus()
        } else if (confirmPass.isEmpty()) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_confirm_password)
            )
            editTextConfirmPassword.requestFocus()
        } else if (confirmPass != pass) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_password_mitch_match)
            )
            editTextConfirmPassword.requestFocus()
        } else {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_update_successfully)
            )

        }
    }

}