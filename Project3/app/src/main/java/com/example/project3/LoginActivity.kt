package com.example.project3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.ContentInfoCompat.Flags
import com.example.project3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val TAG: String = LoginActivity::class.java.simpleName

    private lateinit var binding: ActivityLoginBinding

    private var hideShow: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        setIconColor()

        Log.i(TAG, "onCreate: Login")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Login")

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Login")
        binding.editTextEmail.setText("123@gmail.com")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Login")
//        binding.editTextEmail.setText("123@gmail.com")
    }

    override fun onStop() {
        super.onStop()
//        binding.editTextEmail.setText("abc@gmail.com")
        Log.i(TAG, "onStop: Login")
    }

    override fun onRestart() {
        super.onRestart()
//        binding.editTextEmail.setText("123@gmail.com")
        Log.i(TAG, "onRestart: Login")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Login")
        binding.editTextEmail.setText("123@gmail.com")
    }


    private fun setClickListener() = with(binding) {
        imageViewHideShow.setOnClickListener {
            if (hideShow) {
                hideShow = false
                imageViewHideShow.setImageResource(R.drawable.ic_hide)
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                hideShow = true
                imageViewHideShow.setImageResource(R.drawable.ic_show)
                editTextPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        buttonSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity ,SignUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
//            CommonMethod.moveToNextScreen(this@LoginActivity, SignUpActivity::class.java)
        }

        buttonLogIn.setOnClickListener {
            checkValidation()
        }
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
//            CommonMethod.moveToNextScreen(this@LoginActivity, MainActivity::class.java)
        }
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    private fun setIconColor() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        if (email.isNotEmpty()) {
            editTextEmail.compoundDrawableTintList =
                ContextCompat.getColorStateList(this@LoginActivity, R.color.blue)
        }
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            editTextEmail.error = getString(R.string.please_enter_an_email_address)
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = getString(R.string.please_enter_a_valid_email_address)
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            editTextPassword.error = getString(R.string.please_enter_a_password)
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            editTextPassword.error = getString(R.string.password_length_must_be_6_digits)
            editTextPassword.requestFocus()
        } else {
            CommonMethod.showMessage(this@LoginActivity, getString(R.string.login_successfully))
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            intent.putExtra(CommonMethod.EMAIL,email)
            intent.putExtra(CommonMethod.PASS,pass)
            startActivity(intent)
//            CommonMethod.moveToNextScreen(this@LoginActivity, SignUpActivity::class.java)
        }
    }

}