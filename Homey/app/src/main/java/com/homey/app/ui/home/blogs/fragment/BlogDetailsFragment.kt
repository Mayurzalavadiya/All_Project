package com.homey.app.ui.home.blogs.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentBlogDetailsBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.blogs.model.BlogData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogDetailsFragment : BaseFragment<FragmentBlogDetailsBinding>() {


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentBlogDetailsBinding {
        return FragmentBlogDetailsBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setClickListener()
    }

    private fun setClickListener() = with(binding){
        imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }

}