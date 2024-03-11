package com.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.task3.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()

    }

    private fun setClickListener() = with(binding) {
        buttonSignUp.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirmPassword.text.toString().trim()

        if (email.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_an_email_address))
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(getString(R.string.validation_please_enter_a_valid_email_address))
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_a_password))
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            showMessage(getString(R.string.validation_password_length_must_be_6_digits))
            editTextPassword.requestFocus()
        } else if (confirmPass.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_a_confirm_password))
            editTextConfirmPassword.requestFocus()
        } else if (confirmPass != pass) {
            showMessage(getString(R.string.validation_password_mitch_match))
            editTextConfirmPassword.requestFocus()
        }else {
            showMessage(getString(R.string.validation_sign_in_successfully))
            moveToNextPage(MainActivity::class.java)
            finish()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToNextPage(nextClass: Class<*>) {
        val intent = Intent(this, nextClass)
        startActivity(intent)
    }
}