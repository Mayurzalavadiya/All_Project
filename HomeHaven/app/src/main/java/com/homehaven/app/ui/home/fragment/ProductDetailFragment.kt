package com.homehaven.app.ui.home.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
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
        val getImage = arguments?.let { requireArguments().getInt(Key.IMAGE) }
        if (getImage != null) {
            imageviewProduct.setImageResource(getImage)
            imageviewProduct.mMatrix

//            image.setMaxZoom(4f);
        }



    }


}