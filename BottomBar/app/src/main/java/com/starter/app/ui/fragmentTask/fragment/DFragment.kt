package com.starter.app.ui.fragmentTask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.starter.app.R
import com.starter.app.databinding.FragmentDBinding
import com.starter.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DFragment : BaseFragment<FragmentDBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentDBinding {
      return FragmentDBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        binding.button.setOnClickListener {
//            navigator.load(DFragment::class.java).clearHistory("A")
//            navigator.goBack()
        }
    }
}