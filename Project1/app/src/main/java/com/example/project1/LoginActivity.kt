package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.OnBackPressedCallback
import com.example.project1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.buttonLogIn.setOnClickListener {
            checkValidation()
        }

        binding.imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun checkValidation() {
        val email = binding.editTextEmail.text.toString().trim()
        val pass = binding.editTextPassword.text.toString().trim()


        if (email.isEmpty()) {
            binding.editTextEmail.error = getString(R.string.please_enter_an_email_address)
            binding.editTextEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = getString(R.string.please_enter_a_valid_email_address)
            binding.editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            binding.editTextPassword.error = getString(R.string.please_enter_a_password)
            binding.editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            binding.editTextPassword.error = getString(R.string.password_length_must_be_6_digits)
            binding.editTextPassword.requestFocus()
        } else {
            CommonMethod.showMessage(this, getString(R.string.login_successfully))
            CommonMethod.moveToNextScreen(this, MainActivity::class.java)
            finish()
        }
    }
    }