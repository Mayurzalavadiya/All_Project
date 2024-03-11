package com.example.sample26.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.sample26.R
import com.example.sample26.databinding.FragmentOnboardingBinding
import com.example.sample26.ui.base.BaseFragment

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    private var count: Int = 0
    private var title: String = R.string.your_order_at_your_door_in_minutes_2.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title =
                requireArguments().getString("Title").toString()
            count = requireArguments().getInt("Count")
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(layoutInflater, container, attachedToParent)
    }

    override fun bindData() {
        setClickListener()
    }


    override fun onStart() {
        super.onStart()
        setTextColor()
    }

    private fun setClickListener() = with(binding) {

        buttonNext.setOnClickListener {
            count++
            val onboardingFragment = OnboardingFragment()
            when (count) {
                1 -> {
                    onboardingFragment.arguments = Bundle().apply {
                        putString(
                            "Title",
                            getString(R.string.your_order_at_your_door_in_minutes_2)
                        )
                        putInt("Count", count)
                    }
                    loadFragment(onboardingFragment, isAllowBackStack = false)
                }

                2 -> {
                    onboardingFragment.arguments = Bundle().apply {
                        putString(
                            "Title",
                            getString(R.string.your_order_at_your_door_in_minutes_3)
                        )
                        putInt("Count", count)
                    }
                    loadFragment(onboardingFragment, isAllowBackStack = false)
                }

                3 -> {
                    val loginFragment= LoginFragment()
                    loadFragment(loginFragment, isAllowBackStack = false)
                }

            }
        }
    }

    private fun setTextColor() = with(binding) {


        val ss = SpannableString(title.toString())

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(requireActivity(), R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 23, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewTitle.text = ss
        textViewTitle.movementMethod = LinkMovementMethod.getInstance()

    }
}