package com.starter.app.ui.fragmentTask.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.starter.app.databinding.FragmentABinding
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.manager.Passable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AFragment : BaseFragment<FragmentABinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentABinding {
        return FragmentABinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        binding.button.setOnClickListener {
            navigator.load(BFragment::class.java).hasData(object : Passable<BFragment> {
                override fun passData(t: BFragment) {
                    t.setCallBack {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                        showMessage(it)
                    }
                }
            }).replace(true)
        }
    }
}