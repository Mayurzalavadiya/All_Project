package com.example.sample26.fragment

import android.content.Intent
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
import com.example.sample26.activity.HomeActivity
import com.example.sample26.R
import com.example.sample26.databinding.FragmentLoginBinding
import com.example.sample26.pojo.request.UserLoginRequest
import com.example.sample26.pojo.response.UserResponseUnSuccessFull
import com.example.sample26.ui.base.BaseFragment
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.blue)
        setClickListener()
    }

    override fun onStart() {
        super.onStart()
        setTextColor()

    }

    private fun setTextColor() = with(binding) {
        val ss =
            SpannableString(getString(R.string.log_in_to_hyperlink))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                showMessage(

                    "Terms of Service"
                )
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 10, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewTitle.text = ss
        textViewTitle.movementMethod = LinkMovementMethod.getInstance()


        val ss1 =
            SpannableString(getString(R.string.don_t_have_an_account_sign_up))
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        SignUpFragment(),
                    )
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.white)
            }
        }
        ss1.setSpan(clickableSpan1, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        ss1.setSpan( StyleSpan(Typeface.BOLD), 23,30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textViewNoAccount.text = ss1
        textViewNoAccount.movementMethod = LinkMovementMethod.getInstance()
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

        buttonLogin.setOnClickListener {
            checkValidation()
        }

        textViewForgotPassword.setOnClickListener {
            val forgotPasswordFragment = ForgotPasswordFragment()
            loadFragment(forgotPasswordFragment)
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.fragmentContainer,
//                    ForgotPasswordFragment(),
//                    ForgotPasswordFragment::class.java.simpleName
//                )
//                .addToBackStack(ForgotPasswordFragment::class.java.simpleName)
//                .commit()
        }
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
        } else {
            loginApiCall()
        }
    }


    private fun loginApiCall() {

        showProgressDialog(requireContext())
        val apiServices = Const.create()

        val userLoginRequest = UserLoginRequest().apply {
            email = binding.editTextEmail.text.toString().trim()
            password = binding.editTextPassword.text.toString().trim()
        }

        CoroutineScope(Dispatchers.Main).launch(scopeExceptionHandling) {
            val loginUser = withContext(Dispatchers.IO) { apiServices.loginUser(userLoginRequest) }

            if (loginUser != null) {
                hideProgressDialog()
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                intent.putExtra(Const.TOKEN, loginUser.token)
                startActivity(intent)
                savePrefsVal(IS_LOGIN, true)
                requireActivity().finish()
            } else {
                hideProgressDialog()
                showMessage(getString(R.string.data_not_available))
            }
        }


//        val userLoginResponse = object : Callback<UserLoginResponseSuccessFull> {
//            override fun onResponse(
//                call: Call<UserLoginResponseSuccessFull>,
//                response: Response<UserLoginResponseSuccessFull>
//            ) {
//                if (response.isSuccessful) {
//                    Const.hideProgressDialog()
//                    if (response.body() != null) {
//                        val intent = Intent(requireActivity(), HomeActivity::class.java)
//                        intent.putExtra(Const.TOKEN, response.body()?.token)
//                        startActivity(intent)
//                        savePrefsVal(IS_LOGIN, true)
//                        requireActivity().finish()
//                    }
//                } else {
//                    Const.hideProgressDialog()
//                    val jsonObject = JSONObject(
//                        response.errorBody()!!.charStream().readText()
//                    ).getString("error")
//                    Const.showMessage(requireActivity(), jsonObject)
//                }
//
//            }
//
//            override fun onFailure(call: Call<UserLoginResponseSuccessFull>, t: Throwable) {
//                Const.hideProgressDialog()
//                Const.showMessage(requireActivity(), t.message.toString())
//            }
//        }

//        apiServices.loginUser(userLoginRequest).enqueue(userLoginResponse)
    }

    private fun parseErrorBody(readText: String?): String {
        if (!readText.isNullOrEmpty()) {
            val result = Gson().fromJson(readText, UserResponseUnSuccessFull::class.java)
            return result.error.toString()
        }
        return ""
    }
}