package com.tinder.app.ui.auth.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tinder.app.core.Session
import com.tinder.app.data.pojo.request.LoginRequest
import com.tinder.app.databinding.AuthFragmentLoginBinding
import com.tinder.app.exception.ApplicationException
import com.tinder.app.ui.auth.viewmodel.LoginViewModel
import com.tinder.app.ui.base.BaseFragment
import com.tinder.app.ui.activity.HomeActivity
import com.tinder.app.utils.Validator
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
                validator.submit(binding.editTextEmail)
                    .checkEmpty().errorMessage("Please enter email")
                    .checkValidEmail().errorMessage("Please enter valid email address")
                    .check()

                validator.submit(binding.editTextPassword)
                    .checkEmpty().errorMessage("Please enter password")
                    .check()

                validator.submit(binding.editTextConfirmPassword)
                    .checkEmpty().errorMessage("Please enter confirm password")
                    .matchString(binding.editTextPassword.text.toString())
                    .errorMessage("Please enter valid password")
                    .check()

                if (!binding.checkBoxTC.isChecked) {
                    throw  ApplicationException("Please select checkbox")
                }

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    private val data by lazy {
        arguments?.getString("data") ?: ""
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
        Log.e("Data", data)
        binding.buttonLogin.setOnClickListener {
            if (isValid) {
                callLoginApi()
            }
        }
        binding.buttonRegister.setOnClickListener {
            navigator.load(SignupFragment::class.java).replace(true, "Test")
        }
    }

    override fun onBackActionPerform(): Boolean {
        return false
    }

    /**
     * API calling methods
     */
    private fun callLoginApi() {
        val request = LoginRequest(
            countryCode = "+1",
            phone = "1313131313",
            password = "123456",
            deviceId = "123",
            deviceType = "A"
        )
        loginViewModel.login(request)
    }

    /**
     * LiveData observers
     */
    private fun observeLiveData() {
        loginViewModel.loginLiveData.observe(this, { responseBody ->
            navigator.loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
        })
    }
}