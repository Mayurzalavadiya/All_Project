package com.asis.fragment

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.asis.Activity.CommonClass
import com.asis.Activity.HomeActivity
import com.asis.Activity.MainActivity
import com.asis.R
import com.asis.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var hideShow: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
        setTermsCondition()
    }

    private fun setTermsCondition() = with(binding) {
        val ss = SpannableString(getString(R.string.by_continuing_you_agree_to_the_nterms_of_service_and_privacy_policy))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                CommonClass.showMessage(
                    requireContext(),
                   "Terms of Service"
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(),R.color.black)
            }
        }
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                CommonClass.showMessage(
                    requireContext(),
                    "Privacy Policy"
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(),R.color.black)
            }
        }
        ss.setSpan(clickableSpan, 31, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(clickableSpan1, 52, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewTermsCondition.text = ss
        textViewTermsCondition.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setClickListener() = with(binding) {
        textViewForgotPassword.setOnClickListener{
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    ForgotPasswordFragment(),
                    ForgotPasswordFragment::class.java.simpleName
                )
                .addToBackStack(ForgotPasswordFragment::class.java.simpleName)
                .commit()
        }

        imageViewEye.setOnClickListener {
            if (hideShow) {
                hideShow = false
                imageViewEye.setImageResource(R.drawable.ic_hide)
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                hideShow = true
                imageViewEye.setImageResource(R.drawable.ic_eye)
                editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        constraintLayoutNewUser.setOnClickListener {

            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    CreateAccountFragment(),
                    CreateAccountFragment::class.java.simpleName
                )
                .addToBackStack(CreateAccountFragment::class.java.simpleName)
                .commit()
        }
        buttonNewUser.setOnClickListener {

            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    CreateAccountFragment(),
                    CreateAccountFragment::class.java.simpleName
                )
                .addToBackStack(CreateAccountFragment::class.java.simpleName)
                .commit()
        }
        buttonLogin.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val mobileNumber = editTextMobileNumber.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()


        if (mobileNumber.isEmpty()) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_mobile_number)
            )
            editTextMobileNumber.requestFocus()
        } else if (mobileNumber.length != 10) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_valid_mobile_number)
            )
            editTextMobileNumber.requestFocus()
        } else if (pass.isEmpty()) {
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
        } else {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_login_successfully)
            )
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            intent.putExtra(CommonClass.MOBILE_NUMBER, mobileNumber)
            intent.putExtra(CommonClass.PASS, pass)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}