package com.starter.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.starter.app.R
import com.starter.app.data.pojo.request.LoginVerifyOtpRequest
import com.starter.app.data.pojo.request.SendOtpRequest
import com.starter.app.databinding.FragmentOtpVerificationBinding
import com.starter.app.di.DiConstants
import com.starter.app.exception.ApplicationException
import com.starter.app.ui.auth.viewmodel.AuthViewModel
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.home.activity.ProfileActivity
import com.starter.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OtpVerificationFragment : BaseFragment<FragmentOtpVerificationBinding>() {


    private lateinit var countDownTimer: CountDownTimer
    private var timeRemaining = 30000L // 30 seconds in milliseconds

    private lateinit var mobileNumber: String

    @Inject
    lateinit var validator: Validator

    private val authViewModel: AuthViewModel by viewModels()

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.editTextOtp1).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()
                validator.submit(binding.editTextOtp2).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()
                validator.submit(binding.editTextOtp3).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()
                validator.submit(binding.editTextOtp4).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()
                validator.submit(binding.editTextOtp5).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()
                validator.submit(binding.editTextOtp6).checkEmpty()
                    .errorMessage(getString(R.string.please_enter_otp)).check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): FragmentOtpVerificationBinding {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.white)
        return FragmentOtpVerificationBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun bindData() {
        requestFocus()
        getArgument()
        setUpOtpTextWatcher()
        startCountdownTimer()
        setClickListener()
    }

    private fun requestFocus() {
        binding.editTextOtp1.requestFocus()
        showKeyBoard()

    }


    private fun observeLiveData() {
        authViewModel.verifyOtpLiveData.observe(
            this,
            onChange = {
                DiConstants.hideProgressDialog()
                appPreferences.putBoolean(DiConstants.IS_LOGIN, true)
                session.user = it.data
                session.userSession = it.data?.token.toString()
                navigator.loadActivity(ProfileActivity::class.java).byFinishingAll().start()
            }
        )

        authViewModel.sendOtpLiveData.observe(this, onChange = {
            DiConstants.hideProgressDialog()
            showSnackBar(getString(R.string.otp_sent_successfully))
            disableResendButton()
            resetCountdownTimer()
        })
    }

    private fun callSendOtpApi() {
        DiConstants.showProgressDialog(requireActivity())
        val request = SendOtpRequest(
            contactNo = mobileNumber,
        )
        authViewModel.sendOtp(request)
    }

    private fun getArgument() {
        mobileNumber = arguments?.getString(DiConstants.MOBILE_NUMBER).toString()
        arguments?.let {
            if (Build.VERSION.SDK_INT >= 24) {
                binding.textviewDes.text = Html.fromHtml(
                    getString(
                        R.string.please_enter_the_otp_we_have_sent_to_your_phone_number_b_91_b,
                        mobileNumber
                    ), Html.FROM_HTML_MODE_LEGACY
                )
            } else {
                binding.textviewDes.text = Html.fromHtml(
                    getString(
                        R.string.please_enter_the_otp_we_have_sent_to_your_phone_number_b_91_b,
                        mobileNumber
                    )
                )
            }
        }
    }


    private fun setClickListener() = with(binding) {
        imageViewBackArrow.setOnClickListener {
            countDownTimer.cancel()
            navigator.goBack()
        }

        textviewResendOtpWithSecond.setOnClickListener {
            callSendOtpApi()

        }
    }


    private fun callOtpVerifyApi(otp: String) {
        DiConstants.showProgressDialog(requireActivity())
        val request = LoginVerifyOtpRequest(
            contactNo = mobileNumber,
            otp = otp
        )
        authViewModel.verifyOtp(request)
    }

    private val otpText = mutableListOf("", "", "", "", "", "")
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
        editTextOtp4.addTextChangedListener(GenericTextWatcher(editTextOtp4, editTextOtp5) {
            otpText[3] = it
        })
        editTextOtp5.addTextChangedListener(GenericTextWatcher(editTextOtp5, editTextOtp6) {
            otpText[4] = it
        })
        editTextOtp6.addTextChangedListener(GenericTextWatcher(editTextOtp6, null) {
            otpText[5] = it
        })


        editTextOtp1.setOnKeyListener(GenericKeyEvent(editTextOtp1, null))
        editTextOtp2.setOnKeyListener(GenericKeyEvent(editTextOtp2, editTextOtp1))
        editTextOtp3.setOnKeyListener(GenericKeyEvent(editTextOtp3, editTextOtp2))
        editTextOtp4.setOnKeyListener(GenericKeyEvent(editTextOtp4, editTextOtp3))
        editTextOtp5.setOnKeyListener(GenericKeyEvent(editTextOtp5, editTextOtp4))
        editTextOtp6.setOnKeyListener(GenericKeyEvent(editTextOtp6, editTextOtp5))
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
                R.id.editTextOtp4 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.editTextOtp5 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.editTextOtp6 -> if (text.length == 1) {

                    val otp = otpText.joinToString("")

                    if (isValid) {
                        if (otp == "111111") {
                            countDownTimer.cancel()
                            callOtpVerifyApi(otp)
                        } else {
                            showMessage(getString(R.string.please_enter_valid_otp))
                        }

                    }
                }
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

    private fun startCountdownTimer() {
        countDownTimer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                enableResendButton()
            }
        }.start()
    }

    private fun resetCountdownTimer() {
        // Cancel the current timer
        countDownTimer.cancel()

//        // Reset time remaining to 60 seconds
        timeRemaining = 30000L

        // Start the countdown timer again
        startCountdownTimer()

        // Disable the "Resend OTP" button until the new timer finishes

    }

    @SuppressLint("SetTextI18n", "StringFormatMatches")
    private fun updateTimerText() = with(binding) {
        val seconds = timeRemaining / 1000
        textviewResendOtpWithSecond.text = getString(R.string.resend_code_in_sec, seconds)
    }

    private fun enableResendButton() = with(binding) {
        textviewResendOtpWithSecond.isClickable = true
        textviewResendOtpWithSecond.text = getString(R.string.resend_code)
        textviewResendOtpWithSecond.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.Purple
            )
        )
    }

    private fun disableResendButton() = with(binding) {
        textviewResendOtpWithSecond.isClickable = false
        textviewResendOtpWithSecond.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.DarkGrey
            )
        )
    }

    override fun onBackActionPerform(): Boolean {
        countDownTimer.cancel()
        return true
    }

    fun clearOtp(editTextFirst: AppCompatEditText) {
        editTextFirst.setText("")
    }

    private fun showSnackBar(message: String) {
        if (view != null) {
            val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            snackBar.duration = 3000
            snackBar.setActionTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.white
                )
            )
            val snackView = snackBar.view
            val textView =
                snackView.findViewById(R.id.snackbar_text) as TextView
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.right, 0, 0, 0)
            textView.compoundDrawablePadding =
                resources.getDimensionPixelOffset(R.dimen._5sdp)
            snackView.setBackgroundColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.Green
                )
            )
            snackBar.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}