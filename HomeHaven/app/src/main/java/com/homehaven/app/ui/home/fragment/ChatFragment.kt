package com.homehaven.app.ui.home.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homehaven.app.databinding.FragmentChatBinding
import com.homehaven.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {

    }
}