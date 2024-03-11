package com.bankpick.app.ui.auth.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bankpick.app.R
import com.bankpick.app.databinding.AuthFragmentLoginBinding
import com.bankpick.app.exception.ApplicationException
import com.bankpick.app.ui.activity.HomeActivity
import com.bankpick.app.ui.base.BaseFragment
import com.bankpick.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {

    @Inject
    lateinit var validator: Validator


    private val isValid: Boolean
        get() {
            return try {

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(R.string.validation_please_enter_email)
                    .checkValidEmail()
                    .errorMessage(R.string.validation_please_enter_valid_email_address)
                    .check()

                validator.submit(binding.edittextPassword)
                    .checkEmpty().errorMessage(R.string.validation_please_enter_password)
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
        setTermsCondition()
    }

    private fun setClickListener() = with(binding) {
        buttonSignIn.setOnClickListener {
            if (isValid) {
                navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
            }
        }

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
    }

      //I agree to Terms & Condition
      private fun setTermsCondition() = with(binding) {

          val ss = SpannableString(getString(R.string.textview_i_m_a_new_user_sign_in))
          val clickableSpan: ClickableSpan = object : ClickableSpan() {
              override fun onClick(widget: View) {
                  navigator.load(SignupFragment::class.java).replace(true)
              }

              override fun updateDrawState(ds: TextPaint) {
                  super.updateDrawState(ds)
                  ds.isUnderlineText = false
                  ds.color = ContextCompat.getColor(requireActivity(),R.color.Blue)
              }
          }
          ss.setSpan(clickableSpan, 15, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
          textviewNewUser.text = ss
          textviewNewUser.movementMethod = LinkMovementMethod.getInstance()

      }

}