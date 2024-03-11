package com.homehaven.app.ui.auth.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.homehaven.app.R
import com.homehaven.app.core.Session
import com.homehaven.app.databinding.AuthFragmentLoginBinding
import com.homehaven.app.exception.ApplicationException
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.ui.activity.HomeActivity
import com.homehaven.app.utils.Validator
import com.homehaven.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var session: Session


    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.inputEditTextEmail)
                    .checkEmpty().errorMessage("Please enter email")
                    .checkValidEmail().errorMessage("Please enter valid email address")
                    .check()

                validator.submit(binding.inputEditTextPassword)
                    .checkEmpty().errorMessage("Please enter password")
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { titleBar(it, R.color.ColorBackground, true) }
    }

    override fun bindData() {
        setTermsCondition()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonLogin.setOnClickListener {
            if (isValid) {
                navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
            }
        }
    }


    //I agree to Terms & Condition
    private fun setTermsCondition() = with(binding) {

        val ss = SpannableString(getString(R.string.textview_don_t_have_an_account_register))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navigator.load(SignupFragment::class.java).replace(false)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
            }
        }
        ss.setSpan(clickableSpan, 23, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textviewRegister.text = ss
        textviewRegister.movementMethod = LinkMovementMethod.getInstance()

    }
}