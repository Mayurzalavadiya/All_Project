package com.rapido.app.ui.auth.fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.fragment.app.viewModels
import com.rapido.app.core.Session
import com.rapido.app.data.pojo.request.SignUpRequest
import com.rapido.app.di.DiConstants
import com.rapido.app.exception.ApplicationException
import com.rapido.app.ui.activity.IsolatedActivity
import com.rapido.app.ui.auth.viewmodel.SignUpViewModel
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.utils.Validator
import com.rapido.app.utils.setPasswordToggle
import com.rapido.app.R
import com.rapido.app.databinding.AuthFragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {
    @Inject
    lateinit var session: Session

    private val signViewModel: SignUpViewModel by viewModels()

    private var items: String? = null

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextFullName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_full_name))
                    .check()

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()

                validator.submit(binding.edittextPhoneNumber)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_phone_number))
                    .checkMaxDigits(10)
                    .errorMessage(getString(R.string.validation_please_enter_valid_phone_number))
                    .check()

                validator.submit(binding.edittextPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_password))
                    .checkMinDigits(6).errorMessage("Please enter min 6 digit password")
                    .checkMaxDigits(8).errorMessage("Please enter max 8 digit password")
                    .check()
                validator.submit(binding.edittextConfirmPassword)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_confirm_password))
                    .matchString(binding.edittextPassword.text.toString())
                    .errorMessage(getString(R.string.validation_password_mitchmatch))
                    .check()


                validator.submit(binding.edittextInterest)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_select_interests))
                    .check()



                if (!binding.checkBoxTerms.isChecked) {
                    throw ApplicationException(getString(R.string.validation_please_select_terms))
                }

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setTermsCondition()
        setClickListener()
    }

   //By continuing, you agree to the Terms of Service and Privacy Policy.
     private fun setTermsCondition() = with(binding) {
           val ss =
               SpannableString(getString(R.string.textview_by_continuing_you_agree_to_the_terms_of_service_and_privacy_policy))
           val clickableSpan: ClickableSpan = object : ClickableSpan() {
               override fun onClick(widget: View) {
                   showMessage("Terms & Condition")

               }

               override fun updateDrawState(ds: TextPaint) {
                   super.updateDrawState(ds)
                   ds.isUnderlineText = false
                   ds.color = ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)
               }
           }

           val clickableSpan1: ClickableSpan = object : ClickableSpan() {
               override fun onClick(widget: View) {
                   showMessage("Privacy Policy")
               }

               @RequiresApi(Build.VERSION_CODES.Q)
               override fun updateDrawState(ds: TextPaint) {
                   ds.isUnderlineText = false
                   ds.color = ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)
               }
           }
           ss.setSpan(clickableSpan, 31, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
           ss.setSpan(clickableSpan1, 52, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
           textviewTerms.text = ss
           textviewTerms.movementMethod = LinkMovementMethod.getInstance()
       }


    private fun observeLiveData() {
        signViewModel.signUpLiveData.observe(this, {
            toggleLoader(false)
            it.data?.let { data ->
                session.userId = data.userId.toString()
                session.userSession = data.token.toString()
                session.user = data

            }
            showMessage(it.message)
            navigator.load(VerifyOtpFragment::class.java)
                .replace(true, "A")
        }, {
            toggleLoader(false)
            return@observe true
        })

    }

    private var startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let {
                    it.getStringExtra(DiConstants.LIST_DATA)?.let { data ->
                        binding.edittextInterest.setText(data)
                        items = data
                    }
                }
            } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                //cancelled
            }
        })

    private fun setClickListener() = with(binding) {

        buttonSignIn.setOnClickListener {
            if (isValid) {
                signupApiCall()
            }
        }

        edittextInterest.setOnClickListener {
            navigator.loadActivity(
                IsolatedActivity::class.java,
                InterestFragment::class.java
            ).addBundle(Bundle().apply {
                putString(DiConstants.SEND_LIST_DATA, items)
            })
                .forResult(startForResult)
                .start()
        }

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        setPasswordToggle(edittextPassword, buttonShowPassword)
        setPasswordToggle(edittextConfirmPassword, buttonShowConfirmPassword)
//        buttonShowPassword.setOnClickListener {
//            if (edittextPassword.transformationMethod
//                    .equals(PasswordTransformationMethod.getInstance())
//            ) {
//                buttonShowPassword.text = getString(R.string.button_hide)
//
//                //Show Password
//                edittextPassword.transformationMethod =
//                    HideReturnsTransformationMethod.getInstance()
//            } else {
//                buttonShowPassword.text = getString(R.string.button_view)
//
//                //Hide Password
//                edittextPassword.transformationMethod =
//                    PasswordTransformationMethod.getInstance()
//            }
//        }
//        buttonShowConfirmPassword.setOnClickListener {
//            if (edittextConfirmPassword.transformationMethod
//                    .equals(PasswordTransformationMethod.getInstance())
//            ) {
//                buttonShowConfirmPassword.text =
//                    getString(R.string.button_hide)
//
//                //Show Password
//                edittextConfirmPassword.transformationMethod =
//                    HideReturnsTransformationMethod.getInstance()
//            } else {
//                buttonShowConfirmPassword.text =
//                    getString(R.string.button_view)
//
//                //Hide Password
//                edittextConfirmPassword.transformationMethod =
//                    PasswordTransformationMethod.getInstance()
//            }
//        }
    }

    private fun signupApiCall() = with(binding) {
        toggleLoader(true)
        val request = SignUpRequest(
            fullName = edittextFullName.text.toString().trim(),
            email = edittextEmail.text.toString().trim(),
            countryCode = "+91",
            wpCountryCode = "+91",
            phone = edittextPhoneNumber.text.toString().trim(),
            wpPhone = edittextPhoneNumber.text.toString().trim(),
            password = edittextPassword.text.toString().trim(),
            deviceId = session.deviceId,
            deviceType = "A",
        )
        signViewModel.signUp(request)
    }

}