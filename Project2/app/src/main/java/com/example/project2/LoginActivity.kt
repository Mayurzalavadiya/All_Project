package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class LoginActivity : AppCompatActivity() {

    private var editTextEmail: AppCompatEditText? = null
    private var editTextPassword: AppCompatEditText? = null
    private var buttonLogIn: AppCompatButton? = null
    private var textViewCreateAccount: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        setClickListener()
    }

    private fun setClickListener() {
        buttonLogIn?.setOnClickListener {
            checkValidation()
        }
        textViewCreateAccount?.setOnClickListener {
            CommonMethod.moveToNextScreen(this,SignUpActivity::class.java)
            finish()
        }

    }


    /*fun check validation*/
    private fun checkValidation() {
        val email = editTextEmail!!.text.toString().trim()
        val pass = editTextPassword!!.text.toString().trim()


        if (email.isEmpty()) {
            editTextEmail?.error = getString(R.string.please_enter_an_email_address)
            editTextEmail?.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail?.error = getString(R.string.please_enter_a_valid_email_address)
            editTextEmail?.requestFocus()
        } else if (pass.isEmpty()) {
            editTextPassword?.error = getString(R.string.please_enter_a_password)
            editTextPassword?.requestFocus()
        } else if (pass.length < 6) {
            editTextPassword?.error = getString(R.string.password_length_must_be_6_digits)
            editTextPassword?.requestFocus()
        } else {
            CommonMethod.showMessage(this,getString(R.string.login_successfully))
            CommonMethod.moveToNextScreen(this,DashboardActivity::class.java)
            finish()
        }
    }

    private fun initView() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogIn = findViewById(R.id.buttonLogIn)
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount)
    }

}