package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class SignUpActivity : AppCompatActivity() {

    private var editTextName: AppCompatEditText? = null
    private var editTextEmail: AppCompatEditText? = null
    private var editTextPassword: AppCompatEditText? = null
    private var textViewLoginIn: AppCompatTextView? = null
    private var buttonSignUp: AppCompatButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
        setClickListener()
    }

    private fun setClickListener() {
        buttonSignUp?.setOnClickListener {
            checkValidation()
        }

        textViewLoginIn?.setOnClickListener {
            CommonMethod.moveToNextScreen(this,LoginActivity::class.java)
            finish()
        }
    }

    private fun initView() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        textViewLoginIn = findViewById(R.id.textViewLoginIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)
    }

    private fun checkValidation() {
        val name = editTextName?.text.toString().trim()
        val email = editTextEmail?.text.toString().trim()
        val pass = editTextPassword?.text.toString().trim()

        if (name.isEmpty()) {
            CommonMethod.showMessage(this,getString(R.string.please_enter_name))
            editTextName?.requestFocus()
        } else if (email.isEmpty()) {
            CommonMethod.showMessage(this,getString(R.string.please_enter_an_email_address))
            editTextEmail?.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            CommonMethod.showMessage(this,getString(R.string.please_enter_a_valid_email_address))
            editTextEmail?.requestFocus()
        } else if (pass.isEmpty()) {
            CommonMethod.showMessage(this,getString(R.string.please_enter_a_password))
            editTextPassword?.requestFocus()
        } else if (pass.length < 6) {
            CommonMethod.showMessage(this,getString(R.string.password_length_must_be_6_digits))
            editTextPassword?.requestFocus()
        } else {
            CommonMethod.showMessage(this,getString(R.string.signup_successfully))
            CommonMethod.moveToNextScreen(this,DashboardActivity::class.java)
            finish()
        }
    }
}