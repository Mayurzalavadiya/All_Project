package com.starter.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.starter.app.ui.auth.adapter.OnBoardingRecyclerAdapter
import com.starter.app.R
import com.starter.app.databinding.AuthFragmentLoginBinding
import com.starter.app.databinding.LoginBottomSheetFragmentBinding
import com.starter.app.ui.auth.pojo.OnBoardingData
import com.starter.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {


    private val onBoardingRecyclerAdapter by lazy { OnBoardingRecyclerAdapter() }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ): AuthFragmentLoginBinding {
        return AuthFragmentLoginBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setAdapter()
        startCountdownTimer()
        setClickListener()
    }


    private fun setAdapter() = with(binding) {
        onBoardingRecyclerAdapter.addItem(getItems())
        viewPager.adapter = onBoardingRecyclerAdapter
    }

    private fun setClickListener() = with(binding) {
        buttonGetStart.setOnClickListener {
            setDialog()
        }
    }

    private fun setDialog() {
            val bottomSheetFragment = LoginBottomSheetFragment(this)
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
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


    private fun getItems(): MutableList<OnBoardingData> {
        return mutableListOf<OnBoardingData>().apply {
            add(
                OnBoardingData(
                    R.drawable.gif_1,
                    getString(R.string.experience_improved_health_outcomes),
                    getString(R.string.digitally_delivered_by_dedicated_health_coaches),
                    R.drawable.slider_1
                ),
            )
            add(
                OnBoardingData(
                    R.drawable.gif_2,
                    getString(R.string.personalised_care),
                    getString(R.string.tailored_to_improve_your_health),
                    R.drawable.slider_2
                ),
            )
            add(
                OnBoardingData(
                    R.drawable.gif_3,
                    getString(R.string.clinically_validated_approach),
                    getString(R.string.improve_your_vitals_and_biomarkers),
                    R.drawable.slider_3
                ),
            )

        }
    }
}


