package com.starter.app.ui.fragmentTask.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starter.app.databinding.FragmentBBinding
import com.starter.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BFragment : BaseFragment<FragmentBBinding>() {


    private var callBack: (String) -> Unit = {}
    fun setCallBack(callback: (String) -> Unit = {}) {
        this.callBack= callback
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentBBinding {
        return FragmentBBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        binding.button.setOnClickListener {
            callBack.invoke("bFragment Button call")
//            navigator.goBack()
//            navigator.load(CFragment::class.java).replace(false, "A")
        }
    }

}