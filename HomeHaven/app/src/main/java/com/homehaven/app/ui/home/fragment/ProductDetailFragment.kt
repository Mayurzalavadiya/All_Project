package com.homehaven.app.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homehaven.app.R
import com.homehaven.app.databinding.FragmentProductDetailBinding
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.utils.Key


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setData()
    }

    private fun setData() = with(binding) {
        val getimage = arguments?.let { requireArguments().getInt(Key.IMAGE) }
        if (getimage != null) {
            imageviewProduct.setImageResource(getimage)
            imageviewProduct.mMatrix

//            image.setMaxZoom(4f);
        }



    }


}