package com.starter.app.ui.auth.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.starter.app.R
import com.starter.app.data.pojo.request.SendOtpRequest
import com.starter.app.databinding.LoginBottomSheetFragmentBinding
import com.starter.app.di.DiConstants
import com.starter.app.exception.ServerException
import com.starter.app.ui.activity.IsolatedActivity
import com.starter.app.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginBottomSheetFragment(val context: LoginFragment) : BottomSheetDialogFragment() {

    private lateinit var binding: LoginBottomSheetFragmentBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginBottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()

    }

    private fun setClickListener() = with(binding) {

        buttonContinue.setOnClickListener {
            callLoginApi(inputEditTextMobileNumber.text.toString().split(" ")[1])
        }

        inputEditTextMobileNumber.addTextChangedListener {
            if (inputEditTextMobileNumber.isFocusable) {
                inputEditTextMobileNumber.text?.let { text ->
                    buttonContinue.isEnabled = text.length > 13
                    if (text.length < 4) {
                        inputEditTextMobileNumber.setText(getString(R.string._91))
                        inputEditTextMobileNumber.setSelection(4)
                    }
                }
            }
        }

    }

    private fun observeLiveData() {
        authViewModel.signUpLiveData.observe(
            context, onChange = {
                dismiss()
                DiConstants.hideProgressDialog()
                context.showMessage(getString(R.string.this_number_is_not_registered_contact_our_support_team_to_register))
            }, onError = {
                when (it) {
                    is ServerException -> {
                        if (it.code == 12) {
                            callSendOtpApi(
                                binding.inputEditTextMobileNumber.text.toString().split(" ")[1]
                            )
                            return@observe false
                        } else {
                            dismiss()
                            return@observe true
                        }
                    }

                    else -> {
                        DiConstants.hideProgressDialog()
                        dismiss()
                        return@observe true
                    }
                }
            }
        )

        authViewModel.sendOtpLiveData.observe(context, onChange = {
            DiConstants.hideProgressDialog()
            dismiss()
            context.navigator.loadActivity(
                IsolatedActivity::class.java,
                OtpVerificationFragment::class.java
            ).addBundle(Bundle().apply {
                putString(
                    DiConstants.MOBILE_NUMBER,
                    binding.inputEditTextMobileNumber.text.toString().replace("+91 ", "")
                )
            }).start()
        }, onError = {
            DiConstants.hideProgressDialog()
            dismiss()
            return@observe true
        })
    }


    private fun callLoginApi(mobileNumber: String) {
        DiConstants.showProgressDialog(requireActivity())
        val request = SendOtpRequest(
            contactNo = mobileNumber,
        )
        authViewModel.signUp(request)
    }

    private fun callSendOtpApi(mobileNumber: String) {
        val request = SendOtpRequest(
            contactNo = mobileNumber,
        )
        authViewModel.sendOtp(request)
    }
}
