package com.homey.app.ui.auth.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.homey.app.R
import com.homey.app.databinding.FragmentCurrencyBottomSheetBinding
import com.homey.app.ui.auth.interfaces.ClickCurrencyListener
import com.homey.app.ui.base.BaseBottomSheet

class CurrencyBottomSheetFragment(val clickCurrencyListener: ClickCurrencyListener, val currency:String) :
    BaseBottomSheet<FragmentCurrencyBottomSheetBinding>() {

    var selected = true

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCurrencyBottomSheetBinding {
        return FragmentCurrencyBottomSheetBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setData()
        setClickListener()
        setColor()
    }
    private fun setData() {
        Log.e("TAG", "setData: $currency", )
        selected = when (currency) {
            "Dollor" -> true
            else -> false
        }
    }


    private fun setClickListener() = with(binding) {
        constraintDollar.setOnClickListener {
            selected = true
            setColor()
        }
        constraintSar.setOnClickListener {
            selected = false
            setColor()
        }

        buttonUpdate.setOnClickListener {
            val language =
                if (selected) textviewDollar.text.toString() else textviewSar.text.toString()
            val image =  if (selected) R.drawable.currency_icon else R.drawable.sar_icon
            clickCurrencyListener.onClick(image, language)
            dismiss()
        }
    }

    override fun destroyViewBinding() {
    }

    fun setColor() = with(binding) {
        if (selected) {
            imageviewCheck.setImageResource(R.drawable.selected_circle_icon)
            constraintDollar.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            imageviewDollar.setImageResource(R.drawable.selected_doller_icon)
            textviewDollar.isEnabled = true

            imageviewCheckSar.setImageResource(R.drawable.selected_circle_icon)
            constraintSar.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            imageviewSar.setImageResource(R.drawable.unselected_sar_icon)
            textviewSar.isEnabled = false
        } else {
            imageviewCheck.setImageResource(R.drawable.unselected_circle_icon)
            constraintDollar.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            imageviewDollar.setImageResource(R.drawable.unselected_doller_icon)
            textviewDollar.isEnabled = false

            imageviewCheckSar.setImageResource(R.drawable.selected_circle_icon)
            constraintSar.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            imageviewSar.setImageResource(R.drawable.selected_sar_icon)
            textviewSar.isEnabled = true
        }
    }

}