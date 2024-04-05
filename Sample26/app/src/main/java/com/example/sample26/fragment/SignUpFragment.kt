package com.example.sample26.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.sample26.Const
import com.example.sample26.R
import com.example.sample26.databinding.FragmentSignUpBinding
import com.example.sample26.pojo.request.UserRegisterRequest
import com.example.sample26.pojo.response.UserRegisterResponseSuccessFull
import com.example.sample26.pojo.response.UserResponseUnSuccessFull
import com.example.sample26.ui.base.BaseFragment
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private var hideShow: Boolean = false
    private var isCheck: Boolean = false


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(layoutInflater, container, attachedToParent)
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
                editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        checkBoxTerms.setOnCheckedChangeListener { _, isChecked ->
            isCheck = isChecked
        }

        buttonSignUp.setOnClickListener {
            checkValidation()
        }
    }

    override fun onStart() {
        super.onStart()
        setTextColor()
    }

    private fun setTextColor() = with(binding) {
        val ss =
            SpannableString(getString(R.string.sign_up_to_hyperlink))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 11, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewTitle.text = ss
        textViewTitle.movementMethod = LinkMovementMethod.getInstance()

        val ss1 =
            SpannableString(getString(R.string.already_have_an_account_log_in))
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        LoginFragment(),
                    )
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.white)
            }
        }
        ss1.setSpan(clickableSpan1, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewAlreadyAccount.text = ss1
        textViewAlreadyAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_an_email_address)
            )
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_a_valid_email_address)
            )
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_a_password)
            )
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            showMessage(
                requireContext().getString(R.string.validation_password_length_must_be_6_digits)
            )
            editTextPassword.requestFocus()
        } else
            if (!isCheck) {
                showMessage(
                    requireContext().getString(R.string.validation_please_check_terms_condition)
                )
            } else {
                registerApiCall()


            }
    }

    private fun registerApiCall() {
        showProgressDialog(requireActivity())

        val apiServices = Const.create()

        val userRegisterRequest = UserRegisterRequest().apply {
            email = binding.editTextEmail.text.toString().trim()
            password = binding.editTextPassword.text.toString().trim()
        }

        CoroutineScope(Dispatchers.Main).launch() {
            val registerUser =
                withContext(Dispatchers.IO) { apiServices.registerUser(userRegisterRequest) }

            if (registerUser != null) {
                hideProgressDialog()
                val otpFragment = OtpFragment()
                otpFragment.arguments = Bundle().apply {
                    putString(Const.TOKEN, registerUser.token)
                }
                replaceWithBackStack(R.id.fragmentContainer, otpFragment)
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(
//                        R.id.fragmentContainer,
//                        otpFragment
//                    )
//                    .addToBackStack(SignUpFragment::class.java.simpleName)
//                    .commit()

            } else {
                hideProgressDialog()
                showMessage( getString(R.string.data_not_available))
            }


        }

        val userRegisterResponseSuccessFull = object : Callback<UserRegisterResponseSuccessFull> {
            override fun onResponse(
                call: Call<UserRegisterResponseSuccessFull>,
                response: Response<UserRegisterResponseSuccessFull>
            ) {
                if (response.isSuccessful) {
                    hideProgressDialog()
                    if (response.body() != null) {


                        val otpFragment = OtpFragment()
                        otpFragment.arguments = Bundle().apply {
                            putString(Const.TOKEN, response.body()?.token)
                        }
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                otpFragment
                            )
                            .addToBackStack(OtpFragment::class.java.simpleName)
                            .commit()
                    }
                } else {
                    hideProgressDialog()
                    val jsonObject = JSONObject(
                        response.errorBody()!!.charStream().readText()
                    ).getString("error")
                    showMessage(jsonObject)
//                    showMessage(
//                        requireActivity(),
//                        parseErrorBody(response.errorBody()?.charStream()?.readText())
//                    )
                }

            }

            override fun onFailure(call: Call<UserRegisterResponseSuccessFull>, t: Throwable) {
                hideProgressDialog()
                showMessage( t.message.toString())
            }

        }


//        apiServices.registerUser(userRegisterRequest).enqueue(userRegisterResponseSuccessFull)
    }

    private fun parseErrorBody(readText: String?): String {
        if (!readText.isNullOrEmpty()) {
            val result = Gson().fromJson(readText, UserResponseUnSuccessFull::class.java)
            return result.error.toString()
        }
        return ""
    }


}