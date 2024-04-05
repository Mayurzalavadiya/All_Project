package com.rapido.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.rapido.app.core.Session
import com.rapido.app.data.pojo.request.VerifyOtpRequest
import com.rapido.app.exception.ApplicationException
import com.rapido.app.ui.auth.viewmodel.SignUpViewModel
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.utils.Validator
import com.rapido.app.R
import com.rapido.app.databinding.FragmentVerifyOtpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VerifyOtpFragment : BaseFragment<FragmentVerifyOtpBinding>() {


    private val signViewModel: SignUpViewModel by viewModels()


    private val otpText = mutableListOf("", "", "", "")

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var session: Session

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextOtp1)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.edittextOtp2)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.edittextOtp3)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()
                validator.submit(binding.edittextOtp4)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_otp))
                    .check()

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

    private fun observeLiveData() {
        signViewModel.getOtpLiveData.observe(this, {
            toggleLoader(false)
        }, {
            toggleLoader(false)
            return@observe true
        })
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentVerifyOtpBinding {
        return FragmentVerifyOtpBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setUpOtpTextWatcher()
        getOtpApiCall()
        setNumber()
        setClickListener()
        startTimer()
    }

    @SuppressLint("SetTextI18n")
    private fun setNumber() {
        session.user?.let {
            binding.edittextPhoneNumber.setText("${it.countryCode}- ${it.phone}")
        }
    }

    private lateinit var countDownTimer: CountDownTimer
        var time = 60 * 1000L

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
                    textviewResendOtp.isEnabled = true
                }
            }.start()
        }

        override fun onDestroy() {
            super.onDestroy()
            countDownTimer.cancel()
        }


    private fun setClickListener() = with(binding) {

        buttonVerify.setOnClickListener {
            if (isValid) {
                val otp =
                    "${edittextOtp1.text}${edittextOtp2.text}${edittextOtp3.text}${edittextOtp4.text}"

                if (otp == "1111") {
                    navigator.load(ManageAddressFragment::class.java)
                        .clearHistory("A").replace(false)
                } else {
                    showMessage(getString(R.string.validation_please_enter_valid_otp))
                }
            }
        }

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

//        imageviewEdit.setOnClickListener {
//            edittextPhoneNumber.isEnabled = true
//            edittextPhoneNumber.requestFocus()
//            showKeyBoard()
//        }

        textviewResendOtp.setOnClickListener {
            getOtpApiCall()
            textviewResendOtp.isEnabled = false
            edittextPhoneNumber.isEnabled = false
            startTimer()
        }

        edittextPhoneNumber.addTextChangedListener {
            if (edittextPhoneNumber.isFocusable) {
                edittextPhoneNumber.text?.let { text ->
                    if (text.length < 5) {
                        edittextPhoneNumber.setText(getString(R.string._91))
                        edittextPhoneNumber.setSelection(4)
                    }
                }
            }
        }

    }

    private fun getOtpApiCall() = with(binding) {
        toggleLoader(true)
        val request = VerifyOtpRequest(
            type = "signup",
            countryCode = session.user?.countryCode.toString(),
            phone = edittextPhoneNumber.text.toString().replace(getString(R.string._91), "")
        )
        signViewModel.getOtp(request)
    }

   private fun setUpOtpTextWatcher() = with(binding) {

           edittextOtp1.addTextChangedListener(GenericTextWatcher(edittextOtp1, edittextOtp2) {
               otpText[0] = it
           })
           edittextOtp2.addTextChangedListener(GenericTextWatcher(edittextOtp2, edittextOtp3) {
               otpText[1] = it
           })
           edittextOtp3.addTextChangedListener(GenericTextWatcher(edittextOtp3, edittextOtp4) {
               otpText[2] = it

           })
           edittextOtp4.addTextChangedListener(GenericTextWatcher(edittextOtp4, null) {
               otpText[3] = it

           })


           edittextOtp1.setOnKeyListener(GenericKeyEvent(edittextOtp1, null))
           edittextOtp2.setOnKeyListener(GenericKeyEvent(edittextOtp2, edittextOtp1))
           edittextOtp3.setOnKeyListener(GenericKeyEvent(edittextOtp3, edittextOtp2))
           edittextOtp4.setOnKeyListener(GenericKeyEvent(edittextOtp4, edittextOtp3))
       }

       inner class GenericKeyEvent internal constructor(
           private val currentView: EditText,
           private val previousView: EditText?
       ) : View.OnKeyListener {
           override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
               if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.edittextOtp1 && currentView.text.isEmpty()) {
                   //If current is empty then previous EditText's number will also be deleted
                   previousView!!.text = null
                   previousView.requestFocus()
                   return true
               }
               return false
           }
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
                   R.id.edittextOtp1 -> if (text.length == 1) nextView!!.requestFocus()
                   R.id.edittextOtp2 -> if (text.length == 1) nextView!!.requestFocus()
                   R.id.edittextOtp3 -> if (text.length == 1) nextView!!.requestFocus()
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