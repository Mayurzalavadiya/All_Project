package com.example.fragmentdemo.task2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.mainActivity.MainActivity
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentOtpVerificationBinding


class OtpVerificationFragment : Fragment() {

    private lateinit var binding: FragmentOtpVerificationBinding
    private var email: String? = null
    private var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            email = requireArguments().getString("Email")
            pass = requireArguments().getString("Pass")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpVerificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewEmail.text = email
        binding.textViewPass.text = pass

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        buttonNext.setOnClickListener {

//            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
//            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()

            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TermsAndConditionsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}