package com.homey.app.ui.home.settings.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.databinding.FragmentSettingBinding
import com.homey.app.di.DiConstants
import com.homey.app.ui.activity.IsolatedActivity
import com.homey.app.ui.auth.fragment.CurrencyBottomSheetFragment
import com.homey.app.ui.auth.fragment.LanguageBottomSheetFragment
import com.homey.app.ui.auth.interfaces.ClickCurrencyListener
import com.homey.app.ui.auth.interfaces.ClickLanguageListener
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, attachToRoot)
    }

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun bindData() {
        titleBar(requireActivity(), R.color.colorPrimary)
        setData()
        setClickListener()
    }

    private fun setData() {
        when (appPreferences.getString(DiConstants.LANGUAGE)) {
            "ar" -> binding.textviewLanguageName.text =
                requireActivity().getString(R.string.textview_arabic)

            "en" -> binding.textviewLanguageName.text =
                requireActivity().getString(R.string.textview_english)

        }
    }

    private var startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let {
                }
            } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                //cancelled
            }
        })

    private fun setClickListener() = with(binding) {
        textviewEditProfile.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, EditProfileFragment::class.java)
                .forResult(startForResult)
                .addBundle(Bundle().apply {
                    putString(Keys.FIRST_NAME, textviewName.text.toString())
                    putString(Keys.EMAIL, textviewEmail.text.toString())
                    putString(Keys.PHONE_NUMBER, textviewPhoneNumber.text.toString())
                    putString(Keys.COUNTRY_CODE, textviewPhoneNumber.text.toString())
                })
                .start()
        }

        textviewLoyaltyPoint.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, LoyaltyHistoryFragment::class.java)
                .start()
        }
        textviewWalletPoint.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, WalletHistoryFragment::class.java)
                .start()
        }
        constraintPaymentMethod.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, PaymentMethodFragment::class.java)
                .start()
        }
        constraintGeneral.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, GeneralFragment::class.java)
                .start()
        }
        constraintLanguage.setOnClickListener {
            setLanguageDialog()
        }
        constraintCurrency.setOnClickListener {
            setCurrencyDialog()
        }
    }

    private fun setLanguageDialog() {
        val languageBottomSheetFragment =
            LanguageBottomSheetFragment(
                LanguageListener(),
                binding.textviewLanguageName.text.toString()
            )
        languageBottomSheetFragment.show(childFragmentManager, languageBottomSheetFragment.tag)
    }

    private fun setCurrencyDialog() {
        Log.e("TAG", "onCreate: ${appPreferences.getString(DiConstants.LANGUAGE)}")

        val currencyBottomSheetFragment =
            CurrencyBottomSheetFragment(
                CurrencyListener(),
                binding.textviewCurrencyName.text.toString()
            )
        currencyBottomSheetFragment.show(childFragmentManager, currencyBottomSheetFragment.tag)
    }


    inner class CurrencyListener : ClickCurrencyListener {
        override fun onClick(image: Int, language: String) = with(binding) {

            textviewCurrencyName.text = language
        }


    }

    inner class LanguageListener : ClickLanguageListener {
        override fun onClick(image: String, language: String) = with(binding) {
            textviewLanguageName.text = language

            when (language) {
                requireActivity().getString(R.string.textview_arabic) -> {
                    appPreferences.putString(DiConstants.LANGUAGE, "ar")
                    updateLanguage("ar")
                }

                requireActivity().getString(R.string.textview_english) -> {
                    appPreferences.putString(DiConstants.LANGUAGE, "en")
                    updateLanguage("en")
                }
            }
        }

    }

}