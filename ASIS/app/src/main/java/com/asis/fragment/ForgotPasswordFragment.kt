package com.asis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.asis.Activity.CommonClass
import com.asis.Activity.MainActivity
import com.asis.R
import com.asis.databinding.FragmentForgotPasswordBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageViewBackArrow.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        buttonGetOtp.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val mobileNumber = editTextMobileNumber.text.toString().trim()


        if (mobileNumber.isEmpty()) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_mobile_number)
            )
            editTextMobileNumber.requestFocus()
        } else if (mobileNumber.length != 10) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_valid_mobile_number)
            )
            editTextMobileNumber.requestFocus()
        } else {


            val dialog = BottomSheetDialog(requireContext())

            val view = layoutInflater.inflate(R.layout.fragment_otp_varificaton, null)

            val btnClose = view.findViewById<AppCompatButton>(R.id.buttonVerify)

            btnClose.setOnClickListener {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_login_successfully)
                )
                dialog.dismiss()
                (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        ResetPasswordFragment(),
                        ResetPasswordFragment::class.java.simpleName
                    )
                    .addToBackStack(ResetPasswordFragment::class.java.simpleName)
                    .commit()
            }
            dialog.setCancelable(false)

            dialog.setContentView(view)

            dialog.show()
        }
    }
}
