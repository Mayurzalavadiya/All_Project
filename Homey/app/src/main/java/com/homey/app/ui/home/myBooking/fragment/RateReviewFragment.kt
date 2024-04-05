package com.homey.app.ui.home.myBooking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentRateReviewBinding
import com.homey.app.ui.base.BaseFragment

class RateReviewFragment : BaseFragment<FragmentRateReviewBinding>() {


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentRateReviewBinding {
        return FragmentRateReviewBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

    }
}