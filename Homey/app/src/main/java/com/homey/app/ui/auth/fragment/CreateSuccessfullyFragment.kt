package com.homey.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentCreateSuccessfullyBinding
import com.homey.app.ui.activity.HomeActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSuccessfullyFragment : BaseFragment<FragmentCreateSuccessfullyBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCreateSuccessfullyBinding {
        return FragmentCreateSuccessfullyBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setData()
        setClickListener()
    }

    private fun setClickListener() {
        binding.buttonGetStarted.setOnClickListener {
            navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        arguments?.let {
            binding.textviewName.text =
                getString(R.string.textview_name, it.getString(Keys.FIRST_NAME))
        }
    }

}