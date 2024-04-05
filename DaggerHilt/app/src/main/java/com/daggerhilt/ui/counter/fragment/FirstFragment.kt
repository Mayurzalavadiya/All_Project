package com.daggerhilt.ui.counter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.daggerhilt.databinding.FragmentFirstBinding
import com.daggerhilt.ui.base.BaseFragment
import com.daggerhilt.ui.counter.viewModel.CounterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    val viewModel: CounterViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() = with(binding) {
        textView.text = viewModel.count.toString()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonNext.setOnClickListener {
            viewModel.count++
            loadFragment(SecondFragment(), isAllowBackStack = false)
        }
    }

}