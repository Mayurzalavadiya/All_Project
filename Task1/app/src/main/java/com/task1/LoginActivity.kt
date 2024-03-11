package com.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.task1.databinding.ActivityLoginBinding

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
        imageViewBack.setOnClickListener {
            commonMethod(SignUpActivity::class.java)
        }
    }

    private fun checkValidation() = with(binding) {
        val email = textInputEditTextEmail.text.toString().trim()
        val pass = textInputEditTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_an_email_address))
            textInputEditTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(getString(R.string.validation_please_enter_a_valid_email_address))
            textInputEditTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_a_password))
            textInputEditTextPassword.requestFocus()
        } else if (pass.length < 6) {
            showMessage(getString(R.string.validation_password_length_must_be_6_digits))
            textInputEditTextPassword.requestFocus()
        } else {
            showMessage(getString(R.string.validation_login_successfully))
            commonMethod(SignUpActivity::class.java)
            finish()
        }
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun commonMethod(nextClass: Class<*>) {
        val intent = Intent(this, nextClass)
        startActivity(intent)
    }

}