package com.homehaven.app.ui.auth.fragment

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import com.homehaven.app.R
import com.homehaven.app.databinding.AuthFragmentSignupBinding
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Base Fragment has T type class, pass viewbinding name on this T type,
 */
@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {

    /**
     * Inject fragmentComponent for dagger
     */
    @Inject
    lateinit var validator: Validator

    /**
     * Create view binding object and return this object for set layout on fragment.
     */
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    /**
     * This method is call when on onViewCreated call from life cycle
     * THis one is used for bind data to control
     */
    override fun bindData() {
        setTerms()
    }


    private fun setTerms() {
        val color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
        val text = SpannableStringBuilder()
            .append(getString(R.string.terms_by_clicking_create_account_you_acknowledge_you_have_read_and_agreed_to_our))
            .color(color) { bold { append(getString(R.string.terms_terms_of_use)) } }
            .append(getString(R.string.terms_and))
            .color(color) { bold { append(getString(R.string.terms_privacy_policy)) } }

        binding.textviewTerms.text = text
    }
}