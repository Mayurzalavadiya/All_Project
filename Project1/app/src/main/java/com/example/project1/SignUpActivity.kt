package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.example.project1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private var editTextEmail: AppCompatEditText? = null
    private var editTextPassword: AppCompatEditText? = null
    private var editTextConfirmPassword: AppCompatEditText? = null
    private var editTextName: AppCompatEditText? = null
    private var buttonSignUp: AppCompatButton? = null
    private var imageViewBack: AppCompatImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_sign_up)

        initView()
        setClickListener()


    }

    private fun setClickListener() {

        buttonSignUp?.setOnClickListener {
            checkValidation()
        }

        imageViewBack?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initView() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        buttonSignUp = findViewById(R.id.buttonSignUp)
        imageViewBack = findViewById(R.id.imageViewBack)
    }

    private fun checkValidation() {
        val name = editTextName?.text.toString().trim()
        val email = editTextEmail?.text.toString().trim()
        val pass = editTextPassword?.text.toString().trim()
        val confirmPass = editTextConfirmPassword?.text.toString().trim()

        if (email.isEmpty()) {
           CommonMethod.showMessage(this, getString(R.string.please_enter_an_email_address))
            editTextEmail?.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            CommonMethod.showMessage(this, getString(R.string.please_enter_a_valid_email_address))
            editTextEmail?.requestFocus()
        } else if (pass.isEmpty()) {
            CommonMethod.showMessage(this, getString(R.string.please_enter_a_password))
            editTextPassword?.requestFocus()
        } else if (pass.length < 6) {
            CommonMethod.showMessage(this, getString(R.string.password_length_must_be_6_digits))
            editTextPassword?.requestFocus()
        } else if (confirmPass.isEmpty()) {
            CommonMethod.showMessage(this, getString(R.string.please_enter_a_confirm_password))
            editTextConfirmPassword?.requestFocus()
        } else if (confirmPass != pass) {
            CommonMethod.showMessage(this, getString(R.string.password_mitch_match))
            editTextConfirmPassword?.requestFocus()
        } else if (name.isEmpty()) {
            CommonMethod.showMessage(this, getString(R.string.please_enter_name))
            editTextName?.requestFocus()
        } else  {
            CommonMethod.showMessage(this, getString(R.string.signup_successfully))
            CommonMethod.moveToNextScreen(this,MainActivity::class.java)
            finish()
        }
    }

}