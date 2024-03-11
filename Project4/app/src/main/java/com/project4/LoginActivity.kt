package com.project4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project4.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setClickListener()

    }

    private fun setClickListener() = with(binding) {
        buttonLogIn.setOnClickListener {
            checkValidation()
        }
        imageViewUpOrange.setOnClickListener {
            CommonMethod.moveToNextScreen(this@LoginActivity,SignUpActivity::class.java)
        }
        imageViewUpPink.setOnClickListener {
            CommonMethod.moveToNextScreen(this@LoginActivity,SignUpActivity::class.java)
        }

    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            CommonMethod.showMessage(this@LoginActivity,getString(R.string.validation_please_enter_an_email_address))
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            CommonMethod.showMessage(this@LoginActivity,getString(R.string.validation_please_enter_a_valid_email_address))
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            CommonMethod.showMessage(this@LoginActivity,getString(R.string.validation_please_enter_a_password))
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            CommonMethod.showMessage(this@LoginActivity,getString(R.string.validation_password_length_must_be_6_digits))
            editTextPassword.requestFocus()
        } else {
            CommonMethod.showMessage(this@LoginActivity,getString(R.string.validation_log_in_successfully))
            CommonMethod.moveToNextScreen(this@LoginActivity,SignUpActivity::class.java)
            finish()
        }
    }
}