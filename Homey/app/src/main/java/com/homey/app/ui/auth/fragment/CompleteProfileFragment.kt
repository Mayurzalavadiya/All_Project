package com.homey.app.ui.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentCompleteProfileBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.Keys
import com.homey.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CompleteProfileFragment : BaseFragment<FragmentCompleteProfileBinding>() {

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextFirstName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_first_name))
                    .matchPatter(getString(R.string.validation_p_l_p_l))
                    .errorMessage(getString(R.string.validation_please_enter_valid_first_name))
                    .check()
                validator.submit(binding.edittextLastName)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_last_name))
                    .matchPatter(getString(R.string.validation_p_l_p_l))
                    .errorMessage(getString(R.string.validation_please_enter_valid_last_name))
                    .check()


                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCompleteProfileBinding {
        return FragmentCompleteProfileBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonSubmit.setOnClickListener {
            if (isValid) {
                navigator.load(CreateSuccessfullyFragment::class.java).setBundle(Bundle().apply {
                    putString(
                        Keys.FIRST_NAME,
                        edittextFirstName.text.toString().trim()
                    )
                })
                    .clearHistory(Keys.FRAGMENT_TAG).replace(false)

            }
        }

    }

}