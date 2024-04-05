package com.asis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asis.Activity.CommonClass
import com.asis.Activity.MainActivity
import com.asis.R
import com.asis.databinding.FragmentCompleteProfileBinding

class CompleteProfileFragment : Fragment() {

    private lateinit var binding: FragmentCompleteProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteProfileBinding.inflate(layoutInflater, container, false)
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

        buttonUpdate.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val city = editTextCity.text.toString().trim()
        val country = editTextCountry.text.toString().trim()

        if (firstName.isEmpty()) {
            CommonClass.showMessage(
                requireContext(),
                requireContext().getString(R.string.validation_please_enter_an_email_address)
            )
            editTextFirstName.requestFocus()
        } else
            if (lastName.isEmpty()) {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_mobile_number)
                )
                editTextLastName.requestFocus()
            } else if (city.isEmpty()) {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_password)
                )
                editTextCity.requestFocus()
            } else if (country.isEmpty()) {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_please_enter_a_confirm_password)
                )
                editTextCountry.requestFocus()
            }  else {
                CommonClass.showMessage(
                    requireContext(),
                    requireContext().getString(R.string.validation_create_successfully)
                )
                (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
                (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
                (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
//            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
//            (requireActivity() as MainActivity).supportFragmentManager.popBackStack()
//                requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ExploreFragment(),CompleteProfileFragment::class.java.simpleName)
                    .addToBackStack(CompleteProfileFragment::class.java.simpleName)
                    .commit()

            }
    }
}