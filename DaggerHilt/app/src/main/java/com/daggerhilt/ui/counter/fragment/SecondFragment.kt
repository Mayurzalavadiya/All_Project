package com.daggerhilt.ui.counter.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.daggerhilt.databinding.FragmentSecondBinding
import com.daggerhilt.ui.base.BaseFragment
import com.daggerhilt.ui.counter.viewModel.CounterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    val viewModel: CounterViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachedToParent: Boolean
    ): FragmentSecondBinding {
        return FragmentSecondBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() = with(binding) {
        textView.text = viewModel.count.toString()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        viewModel.count++
        buttonNext.setOnClickListener { loadFragment(FirstFragment(), isAllowBackStack = false) }
    }
}