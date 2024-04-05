package com.homey.app.ui.home.settings.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.databinding.AlertDialogLayoutBinding
import com.homey.app.databinding.FragmentGeneralBinding
import com.homey.app.ui.activity.AuthActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeneralFragment : BaseFragment<FragmentGeneralBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentGeneralBinding {
        return FragmentGeneralBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        textviewChangePassword.setOnClickListener {
            navigator.load(ChangePasswordFragment::class.java).replace(true)
        }

        textviewFAQ.setOnClickListener {
            navigator.load(FaqFragment::class.java).replace(true)
        }

        textviewTermsConditions.setOnClickListener {
            navigator.load(TermsConditionsFragment::class.java).setBundle(Bundle().apply {
                putString(Keys.TITLE, textviewTermsConditions.text.toString())
            }).replace(true)
        }
        textviewPrivacyPolicy.setOnClickListener {
            navigator.load(TermsConditionsFragment::class.java).setBundle(Bundle().apply {
                putString(Keys.TITLE, textviewPrivacyPolicy.text.toString())
            }).replace(true)
        }
        textviewAboutUs.setOnClickListener {
            navigator.load(TermsConditionsFragment::class.java).setBundle(Bundle().apply {
                putString(Keys.TITLE, textviewAboutUs.text.toString())
            }).replace(true)
        }

        textviewContactUs.setOnClickListener {
            navigator.load(ContactUsFragment::class.java).replace(true)
        }
        textviewLogout.setOnClickListener {
            showAlertDialog()
        }
        textviewDeleteAccount.setOnClickListener { setDialog() }
    }

    private fun setDialog() {
        val deleteAccountBottomSheetFragment = DeleteAccountBottomSheetFragment()
        deleteAccountBottomSheetFragment.show(
            childFragmentManager,
            deleteAccountBottomSheetFragment.tag
        )
    }

    private fun showAlertDialog() {
        val alertDialogLayoutBinding =
            AlertDialogLayoutBinding.inflate(layoutInflater)
        val alertDialog = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
            .create()

        alertDialog.setView(alertDialogLayoutBinding.root)

        alertDialogLayoutBinding.buttonLogout.setOnClickListener {
            alertDialog.dismiss()
            appPreferences.clearAll()
            navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()

        }

        alertDialogLayoutBinding.buttonCancle.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }
}