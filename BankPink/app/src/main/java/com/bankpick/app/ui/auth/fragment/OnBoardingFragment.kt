package com.bankpick.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bankpick.app.R
import com.bankpick.app.databinding.FragmentOnBoardingBinding
import com.bankpick.app.ui.auth.adapter.OnBoardingRecyclerAdapter
import com.bankpick.app.ui.auth.model.OnBoardingData
import com.bankpick.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    private val onBoardingRecyclerAdapter by lazy { OnBoardingRecyclerAdapter() }
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setUpAdapter()
        startCountdownTimer()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonNext.setOnClickListener {
            navigator.load(LoginFragment::class.java).replace(true)
        }
    }

    private fun startCountdownTimer() {
            viewLifecycleOwner.lifecycleScope.launch {
                while (true) {
                    delay(3000)
                    updateTimerText()
                }
            }
        }

        @SuppressLint("SetTextI18n", "StringFormatMatches")
        private fun updateTimerText() = with(binding) {
            if (viewPager.currentItem == getItems().size - 1)
                viewPager.currentItem = 0
            else viewPager.currentItem = viewPager.currentItem + 1
        }


    private fun setUpAdapter() = with(binding) {
        onBoardingRecyclerAdapter.addItem(getItems())
        viewPager.adapter = onBoardingRecyclerAdapter
    }

    private fun getItems(): MutableList<OnBoardingData> {
        return mutableListOf<OnBoardingData>().apply {
            add(
                OnBoardingData(
                    R.drawable.group_1,
                    getString(R.string.textview_fastest_payment_in_the_world),
                    getString(R.string.textview_integrate_multiple_payment_methoods_to_help_you_up_the_process_quickly),
                    R.drawable.slider_1
                ),
            )
            add(
                OnBoardingData(
                    R.drawable.group_2,
                    getString(R.string.textview_the_most_secoure_platfrom_for_customer),
                    getString(R.string.textview_built_in_fingerprint_face_recognition_and_more_keeping_you_completely_safe),
                    R.drawable.slider_2
                ),
            )
            add(
                OnBoardingData(
                    R.drawable.group_3,
                    getString(R.string.textview_paying_for_everything_is_easy_and_convenient),
                    getString(R.string.textview_built_in_fingerprint_face_recognition_and_more_keeping_you_completely_safe),
                    R.drawable.slider_3
                ),
            )

        }
    }

}