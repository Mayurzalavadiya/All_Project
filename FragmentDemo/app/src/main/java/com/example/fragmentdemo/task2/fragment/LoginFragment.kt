package com.example.fragmentdemo.task2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.mainActivity.MainActivity
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentLoginBinding
import com.example.fragmentdemo.mainActivity.BaseActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonLogIn.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()


        if (email.isEmpty()) {
            BaseActivity().showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_an_email_address)
            )
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            BaseActivity().showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_valid_email_address)
            )
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            BaseActivity().showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_a_password)
            )
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            BaseActivity().showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_password_length_must_be_6_digits)
            )
            editTextPassword.requestFocus()
        } else {
            val otpVerificationFragment = OtpVerificationFragment()
            otpVerificationFragment.arguments = Bundle().apply {
                putString("Email", email)
                putString("Pass", pass)
            }
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, otpVerificationFragment,otpVerificationFragment::class.java.simpleName)
                .addToBackStack(otpVerificationFragment::class.java.simpleName)
                .commit()

        }
    }

}