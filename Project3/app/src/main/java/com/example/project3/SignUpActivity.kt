package com.example.project3

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.project3.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val TAG: String = LoginActivity::class.java.simpleName

    private lateinit var binding: ActivitySignUpBinding

    private var hideShow: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setClickListener()
        Log.i(TAG, "onCreate:SignUp ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart:SignUp ")
        binding.editTextEmail.setText(intent?.getStringExtra(CommonMethod.EMAIL))
        binding.editTextPassword.setText(intent?.getStringExtra(CommonMethod.PASS))
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume:SignUp ")

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause:SignUp ")

    }

    override fun onStop() {
        super.onStop()
        binding.editTextEmail.setText("abc@gmail.com")
        Log.i(TAG, "onStop:SignUp ")
    }

    override fun onRestart() {
        super.onRestart()
        binding.editTextEmail.setText("123@gmail.com")
        Log.i(TAG, "onRestart:SignUp ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: SignUp")
    }


    private fun setClickListener() = with(binding) {

        buttonLogIn.setOnClickListener {
            val intent = Intent(this@SignUpActivity ,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
//            CommonMethod.moveToNextScreen(this@SignUpActivity, LoginActivity::class.java)
        }
        buttonSignUp.setOnClickListener {
            checkValidation()
        }

        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

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

    }


    private fun checkValidation() = with(binding) {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        if (name.isEmpty()) {
            CommonMethod.showMessage(this@SignUpActivity, getString(R.string.please_enter_name))
            editTextName.requestFocus()
        } else if (email.isEmpty()) {
            CommonMethod.showMessage(
                this@SignUpActivity,
                getString(R.string.please_enter_an_email_address)
            )
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            CommonMethod.showMessage(
                this@SignUpActivity,
                getString(R.string.please_enter_a_valid_email_address)
            )
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            CommonMethod.showMessage(
                this@SignUpActivity,
                getString(R.string.please_enter_a_password)
            )
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            CommonMethod.showMessage(
                this@SignUpActivity,
                getString(R.string.password_length_must_be_6_digits)
            )
            editTextPassword.requestFocus()
        } else {
            CommonMethod.showMessage(this@SignUpActivity, getString(R.string.signup_successfully))
            CommonMethod.moveToNextScreen(this@SignUpActivity, MainActivity::class.java)
        }
    }

}