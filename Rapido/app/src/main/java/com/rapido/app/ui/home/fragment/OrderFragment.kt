package com.rapido.app.ui.home.fragment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.databinding.HomeFragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : BaseFragment<HomeFragmentOrderBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): HomeFragmentOrderBinding {
        return HomeFragmentOrderBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() = with(binding){
        buttonSend.setOnClickListener {
            val intent = Intent()
            intent.putExtra("my_data", "Return string data")
            requireActivity().setResult(Activity.RESULT_OK, intent)
            requireActivity().finish()
        }

        buttonCancel.setOnClickListener {
            requireActivity().setResult(Activity.RESULT_CANCELED)
            requireActivity().finish()
        }
    }
}