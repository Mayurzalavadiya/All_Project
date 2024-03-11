package com.homey.app.ui.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.homey.app.R
import com.homey.app.databinding.FragmentAccountTypeBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountTypeFragment : BaseFragment<FragmentAccountTypeBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAccountTypeBinding {
        return FragmentAccountTypeBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener { navigator.goBack() }
        constraintCustomer.setOnClickListener { setColor(true) }
        constraintHotel.setOnClickListener { setColor(false) }

        buttonNext.setOnClickListener {
            navigator.load(SignupFragment::class.java).replace(true, Keys.FRAGMENT_MAIN)
        }
    }

    fun setColor(isclicked: Boolean) = with(binding) {
        if (isclicked) {
            imageviewCheck.setImageResource(R.drawable.selected_circle_icon)
            constraintCustomer.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            imageviewCustomer.setImageResource(R.drawable.selected_person_icon)
            textviewCustomer.isEnabled = true

            imageviewCheckHotel.setImageResource(R.drawable.unselected_circle_icon)
            constraintHotel.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            imageviewHotel.setImageResource(R.drawable.unselected_hotel_icon)
            textviewHotel.isEnabled = false
        } else {
            imageviewCheck.setImageResource(R.drawable.unselected_circle_icon)
            constraintCustomer.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            imageviewCustomer.setImageResource(R.drawable.unselected_person_icon)
            textviewCustomer.isEnabled = false

            imageviewCheckHotel.setImageResource(R.drawable.selected_circle_icon)
            constraintHotel.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            imageviewHotel.setImageResource(R.drawable.selected_hotel_icon)
            textviewHotel.isEnabled = true
        }
    }
}