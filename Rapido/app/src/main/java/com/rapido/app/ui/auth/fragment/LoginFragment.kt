package com.rapido.app.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rapido.app.core.Session
import com.rapido.app.data.pojo.request.LoginRequest
import com.rapido.app.exception.ApplicationException
import com.rapido.app.ui.activity.IsolatedActivity
import com.rapido.app.ui.auth.viewmodel.LoginViewModel
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.utils.Validator
import com.rapido.app.utils.setPasswordToggle
import com.rapido.app.R
import com.rapido.app.databinding.AuthFragmentLoginBinding
import com.rapido.app.di.DiConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var session: Session

    private val loginViewModel: LoginViewModel by viewModels()

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()

                validator.submit(binding.edittextPassword)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_password))
                    .checkMinDigits(6).errorMessage("Please enter min 6 digit password")
                    .checkMaxDigits(8).errorMessage("Please enter max 8 digit password")
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
        attachToRoot: Boolean,
    ): AuthFragmentLoginBinding {
        return AuthFragmentLoginBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonSignIn.setOnClickListener {
            if (isValid) {
                callLoginApi()
            }
        }

        textviewCreateNewOne.setOnClickListener {
            navigator.load(SignupFragment::class.java).replace(true,"A")
        }

        setPasswordToggle(edittextPassword, buttonShowPassword)
    }

    /**
     * API calling methods
     */
    private fun callLoginApi() = with(binding) {
        toggleLoader(true)
        val request = LoginRequest(
            email = edittextEmail.text.toString().trim(),
            password = edittextPassword.text.toString().trim(),
            deviceId = session.deviceId,
            deviceType = "A",
        )
        loginViewModel.login(request)
    }

    /**
     * LiveData observers
     */
    private fun observeLiveData() {
        loginViewModel.loginLiveData.observe(this, {
            toggleLoader(false)
            it.data?.let { data ->
                session.userId = data.userId.toString()
                session.userSession = data.token.toString()
                session.user = data
                appPreferences.putBoolean(DiConstants.IS_LOGIN, true)
                showMessage(it.message)
                navigator.loadActivity(
                    IsolatedActivity::class.java,
                    ManageAddressFragment::class.java
                ).byFinishingAll().start()
            }
        }, {
            toggleLoader(false)
            return@observe true
        })
    }
}