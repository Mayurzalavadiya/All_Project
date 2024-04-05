package com.homey.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.homey.app.R
import com.homey.app.databinding.FragmentPhoneVerificationBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.security.Key
import javax.inject.Inject

@AndroidEntryPoint
class PhoneVerificationFragment : BaseFragment<FragmentPhoneVerificationBinding>() {

    private val otpText = mutableListOf("", "", "", "", "", "")

    private lateinit var isSignup: String
    private lateinit var countDownTimer: CountDownTimer
    var time = 60 * 1000L

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.editTextOtp1)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.editTextOtp2)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.editTextOtp3)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.editTextOtp4)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
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
        attachToRoot: Boolean
    ): FragmentPhoneVerificationBinding {
        return FragmentPhoneVerificationBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        getArgument()
        startTimer()
        setUpOtpTextWatcher()
        setClickListener()
    }

    private fun getArgument() {
        arguments?.let {
            val phonenumber = it.getString(Keys.PHONE_NUMBER)
            val code = it.getString(Keys.COUNTRY_CODE)
            isSignup = it.getString(Keys.SIGN_UP).toString()
            binding.textviewPhoneNumber.text =
                getString(R.string.textview_phonenumber, code, phonenumber)
        }
    }

    private fun setClickListener() = with(binding) {
        textviewResendcode.setOnClickListener {
            textviewResendcode.text = getString(R.string.textview_resend_code_in)
            textviewResendcode.isEnabled = false
            startTimer()
        }

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        buttonVerify.setOnClickListener {
            val otp = otpText.joinToString("")

            if (isValid) {
                if (otp == "1111") {
                    countDownTimer.cancel()

                    if (isSignup == Keys.SIGN_UP) {
                        navigator.load(CompleteProfileFragment::class.java)
                            .replace(true,  Keys.FRAGMENT_TAG)
                    } else {
                        navigator.load(ResetPasswordFragment::class.java)
                            .replace(true)
                    }
                } else {
                    showMessage(getString(R.string.validation_please_enter_valid_otp))
                }

            }
        }
    }


    private fun startTimer() = with(binding) {
        countDownTimer = object : CountDownTimer(time, 1000) {

            // Callback function, fired on regular interval
            @SuppressLint("SetTextI18n", "StringFormatMatches")
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished / 1000 < 10) {
                    textviewResendTime.text = getString(R.string._00_0, millisUntilFinished / 1000)
                } else {
                    textviewResendTime.text = getString(R.string._00, millisUntilFinished / 1000)
                }
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                textviewResendTime.text = ""
                textviewResendcode.text = getString(R.string.textview_resend_code)
                textviewResendcode.isEnabled = true
            }
        }.start()
    }

    private fun setUpOtpTextWatcher() = with(binding) {

        editTextOtp1.addTextChangedListener(GenericTextWatcher(editTextOtp1, editTextOtp2) {
            otpText[0] = it
        })
        editTextOtp2.addTextChangedListener(GenericTextWatcher(editTextOtp2, editTextOtp3) {
            otpText[1] = it
        })
        editTextOtp3.addTextChangedListener(GenericTextWatcher(editTextOtp3, editTextOtp4) {
            otpText[2] = it
        })
        editTextOtp4.addTextChangedListener(GenericTextWatcher(editTextOtp4, null) {
            otpText[3] = it
        })


        editTextOtp1.setOnKeyListener(GenericKeyEvent(editTextOtp1, null))
        editTextOtp2.setOnKeyListener(GenericKeyEvent(editTextOtp2, editTextOtp1))
        editTextOtp3.setOnKeyListener(GenericKeyEvent(editTextOtp3, editTextOtp2))
        editTextOtp4.setOnKeyListener(GenericKeyEvent(editTextOtp4, editTextOtp3))
    }

    inner class GenericKeyEvent internal constructor(
        private val currentView: EditText,
        private val previousView: EditText?
    ) : View.OnKeyListener {
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.editTextOtp1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }


    inner class GenericTextWatcher internal constructor(
        private val currentView: View,
        private val nextView: View?,
        private val callback: (String) -> Unit
    ) : TextWatcher {
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            callback((text))
            when (currentView.id) {
                R.id.editTextOtp1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.editTextOtp2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.editTextOtp3 -> if (text.length == 1) nextView!!.requestFocus()


                //You can use EditText4 same as above to hide the keyboard
            }
        }


        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) { // TODO Auto-generated method stub
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) { // TODO Auto-generated method stub
        }

    }
}