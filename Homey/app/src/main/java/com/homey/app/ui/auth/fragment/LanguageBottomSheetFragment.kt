package com.homey.app.ui.auth.fragment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.homey.app.R
import com.homey.app.databinding.FragmentLanguageBottomSheetBinding
import com.homey.app.ui.auth.interfaces.ClickLanguageListener
import com.homey.app.ui.base.BaseBottomSheet

class LanguageBottomSheetFragment(
    val clickLanguageListener: ClickLanguageListener,
    val language: String
) :
    BaseBottomSheet<FragmentLanguageBottomSheetBinding>() {

    var selected = true

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentLanguageBottomSheetBinding {
        return FragmentLanguageBottomSheetBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setData()
        setClickListener()
        setColor()
    }

    private fun setData() {
        selected = when (language) {
            "English" -> true
            else -> false
        }
    }

    private fun setClickListener() = with(binding) {
        constraintEnglish.setOnClickListener {
            selected = true
            setColor()
        }
        constraintArabic.setOnClickListener {
            selected = false
            setColor()
        }

        buttonUpdate.setOnClickListener {
            val language =
                if (selected) textviewEnglish.text.toString() else textviewArabic.text.toString()
            val image = language.get(0).toString()
            clickLanguageListener.onClick(image, language)
            dismiss()
        }
    }

    override fun destroyViewBinding() {
    }

    fun setColor() = with(binding) {
        if (selected) {
            imageviewCheck.setImageResource(R.drawable.selected_circle_icon)
            constraintEnglish.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            textviewEnglish.isEnabled = true

            imageviewCheckArabic.setImageResource(R.drawable.selected_circle_icon)
            constraintArabic.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            textviewArabic.isEnabled = false
        } else {
            imageviewCheck.setImageResource(R.drawable.unselected_circle_icon)
            constraintEnglish.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_gray40per_10px
                )
            )
            textviewEnglish.isEnabled = false

            imageviewCheckArabic.setImageResource(R.drawable.selected_circle_icon)
            constraintArabic.setBackground(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_colorprimary_10px
                )
            )
            textviewArabic.isEnabled = true
        }
    }
}