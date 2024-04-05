package com.starter.app.ui.fragmentTask.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starter.app.databinding.FragmentCBinding
import com.starter.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CFragment : BaseFragment<FragmentCBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCBinding {
        return FragmentCBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        binding.button.setOnClickListener {
            navigator.load(DFragment::class.java).replace(false,"A")
        }
    }
}