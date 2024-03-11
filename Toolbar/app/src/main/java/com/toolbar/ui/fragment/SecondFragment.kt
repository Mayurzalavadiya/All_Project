package com.toolbar.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sample26.ui.base.BaseFragment
import com.toolbar.R
import com.toolbar.databinding.FragmentSecondBinding
import com.toolbar.ui.activity.MainActivity

class SecondFragment : BaseFragment<FragmentSecondBinding>() {



    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentSecondBinding {
       return FragmentSecondBinding.inflate(inflater,container,attachedToParent)
    }

    override fun bindData() {
        setMenu()
    }

    private fun setMenu() {
        if (activity != null) {
            val activity = activity as MainActivity
            activity.supportActionBar?.title = "SecondFragment"
            activity.supportActionBar?.setLogo(R.drawable.baseline_edit_24)
        }
    }
}