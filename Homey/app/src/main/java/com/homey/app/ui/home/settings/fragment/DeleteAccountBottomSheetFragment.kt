package com.homey.app.ui.home.settings.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentDeleteAccountBottomSheetBinding
import com.homey.app.ui.base.BaseBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAccountBottomSheetFragment :
    BaseBottomSheet<FragmentDeleteAccountBottomSheetBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentDeleteAccountBottomSheetBinding {
        return FragmentDeleteAccountBottomSheetBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
    }

    override fun destroyViewBinding() {
        dismiss()
    }
}