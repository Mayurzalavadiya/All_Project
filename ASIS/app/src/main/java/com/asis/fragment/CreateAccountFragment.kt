package com.asis.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.asis.Activity.CommonClass
import com.asis.Activity.MainActivity
import com.asis.R
import com.asis.databinding.FragmentCreateAccountBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    private var hideShowPass: Boolean = false
    private var hideShowConfirmPass: Boolean = false

    private var isCheck: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        setTermsCondition()
    }

    private fun setTermsCondition() = with(binding) {
        val ss =
            SpannableString(getString(R.string.by_continuing_you_agree_to_the_nterms_of_service_and_privacy_policy))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                CommonClass.showMessage(
                    requireContext(),
                    "Terms of Service"
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = requireActivity().resources.getColor(R.color.Green)
            }
        }
//        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                CommonClass.showMessage(
//                    requireContext(),
//                    "Privacy Policy"
//                )
//            }
//
//            override fun updateDrawState(ds: TextPaint) {
//                ds.isUnderlineText = true
//                ds.color = ContextCompat.getColor(requireActivity(), R.color.purple)
//            }
//        }
        ss.setSpan(clickableSpan, 31, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        ss.setSpan(clickableSpan1, 52, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewTermsCondition.text = ss
        textViewTermsCondition.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setClickListener() = with(binding) {

        //
        constraintLayoutAlreadyUser.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    LoginFragment(),
                    LoginFragment::class.java.simpleName
                )
                .addToBackStack(LoginFragment::class.java.simpleName)
                .commit()
        }

        buttonAlreadyUser.setOnClickListener {
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()

            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    LoginFragment(),
                    LoginFragment::class.java.simpleName
                )
                .addToBackStack(LoginFragment::class.java.simpleName)
                .commit()
        }
        //Create Account
        buttonCreate.setOnClickListener {
            checkValidation()
        }
        //Check Terms & Condition
        checkBoxTerms.setOnCheckedChangeListener { _, isChecked ->
            isCheck = isChecked
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
                editTextConfirmPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                hideShowConfirmPass = true
                imageViewConfirmEye.setImageResource(R.drawable.ic_eye)
                editTextConfirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val mobileNumber = editTextMobileNumber.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirmPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_an_email_address)
                )
                editTextEmail.requestFocus()
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_valid_email_address)
                )
                editTextEmail.requestFocus()
            }
            mobileNumber.isEmpty() -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_mobile_number)
                )
                editTextMobileNumber.requestFocus()
            }
            mobileNumber.length != 10 -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_valid_mobile_number)
                )
                editTextMobileNumber.requestFocus()
            }
            pass.isEmpty() -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_password)
                )
                editTextPassword.requestFocus()
            }
            pass.length < 6 -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_password_length_must_be_6_digits)
                )
                editTextPassword.requestFocus()
            }
            confirmPass.isEmpty() -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_confirm_password)
                )
                editTextConfirmPassword.requestFocus()
            }
            confirmPass != pass -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_password_mitch_match)
                )
                editTextConfirmPassword.requestFocus()
            }
            !isCheck -> {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_check_terms_condition)
                )
            }
            else -> {
                val dialog = BottomSheetDialog(requireContext())

                val view = layoutInflater.inflate(R.layout.fragment_otp_varificaton, null)

                val btnClose = view.findViewById<AppCompatButton>(R.id.buttonVerify)
                val editTextOtp1 = view.findViewById<AppCompatEditText>(R.id.editTextOtp1)
                val editTextOtp2 = view.findViewById<AppCompatEditText>(R.id.editTextOtp2)
                val editTextOtp3 = view.findViewById<AppCompatEditText>(R.id.editTextOtp3)
                val editTextOtp4 = view.findViewById<AppCompatEditText>(R.id.editTextOtp4)

                changeListener(editTextOtp1, editTextOtp2)
                changeListener(editTextOtp2, editTextOtp3)
                changeListener(editTextOtp3, editTextOtp4)

                btnClose.setOnClickListener {

                    CommonClass.showMessage(
                        requireContext(),
                        requireContext().getString(R.string.validation_create_successfully)
                    )

                    dialog.dismiss()
                    (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            CompleteProfileFragment(),
                            CompleteProfileFragment::class.java.simpleName
                        )
                        .addToBackStack(CompleteProfileFragment::class.java.simpleName)
                        .commit()
                }
                dialog.setCancelable(false)

                dialog.setContentView(view)

                dialog.show()


            }
        }
    }

    private fun changeListener(
        editTextFirst: AppCompatEditText,
        editTextSecond: AppCompatEditText
    ) {
        editTextFirst.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                val textLength1: Int = editTextFirst.text.toString().trim().length
                if (textLength1 >= 1) {
                    editTextSecond.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }
        })
        editTextSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty()) {
                    editTextFirst.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }
        })
    }
}