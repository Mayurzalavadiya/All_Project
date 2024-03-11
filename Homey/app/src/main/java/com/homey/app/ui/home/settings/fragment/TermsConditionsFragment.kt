package com.homey.app.ui.home.settings.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentTermsConditionsBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsConditionsFragment : BaseFragment<FragmentTermsConditionsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentTermsConditionsBinding {
        return FragmentTermsConditionsBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        arguments?.let {
            binding.textviewTitle.text = it.getString(Keys.TITLE)
        }
        binding.imageviewBack.setOnClickListener {
            navigator.goBack()
        }
    }

}