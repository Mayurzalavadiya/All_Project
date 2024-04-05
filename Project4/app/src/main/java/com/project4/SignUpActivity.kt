package com.project4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project4.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()

    }

    private fun setClickListener() = with(binding) {
        buttonSignUp.setOnClickListener {
            checkValidation()
        }
        imageViewUpOrange.setOnClickListener {
            CommonMethod.moveToNextScreen(this@SignUpActivity,LoginActivity::class.java)
        }
        imageViewUpPink.setOnClickListener {
            CommonMethod.moveToNextScreen(this@SignUpActivity,LoginActivity::class.java)
        }

    }

    private fun checkValidation() = with(binding) {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirmPassword.text.toString().trim()

        if (name.isEmpty()) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_please_enter_name))
            editTextName.requestFocus()
        } else if (email.isEmpty()) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_please_enter_an_email_address))
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_please_enter_a_valid_email_address))
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_please_enter_a_password))
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_password_length_must_be_6_digits))
            editTextPassword.requestFocus()
        } else if (confirmPass.isEmpty()) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_please_enter_a_confirm_password))
            editTextConfirmPassword.requestFocus()
        } else if (confirmPass != pass) {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_password_mitch_match))
            editTextConfirmPassword.requestFocus()
        } else {
            CommonMethod.showMessage(this@SignUpActivity,getString(R.string.validation_sign_up_successfully))
            CommonMethod.moveToNextScreen(this@SignUpActivity,MainActivity::class.java)
            finish()
        }
    }
}