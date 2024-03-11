package com.task1

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.task1.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private var isCheck: Boolean = false

    private var isClick: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        setTermsCondition()


    }

    private fun setTermsCondition() = with(binding) {
        val ss = SpannableString(getString(R.string.i_agree_with_terms_conditions))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@SignUpActivity, "Terms & Condition", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
                ds.color = ContextCompat.getColor(this@SignUpActivity,R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 22, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewCheckTerms.text = ss
        textViewCheckTerms.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setClickListener() = with(binding) {

        constraintLayout.setOnClickListener {


            val animation = TranslateAnimation(
                0.0f,
                128.0f,
                0.0f,
                0.0f
            )
            animation.duration = 200 // animation duration

            animation.repeatMode = 0

            animation.fillAfter = true
            leftThumb.startAnimation(animation)

            val animation1 = TranslateAnimation(
                0.0f,
                -105.0f,
                0.0f,
                0.0f
            )
            animation1.duration = 200 // animation duration

            animation1.repeatMode = 0

            animation1.fillAfter = true
            rightThumb.startAnimation(animation1)
            isClick = 1

            leftThumb.layoutParams.height = 70
            leftThumb.layoutParams.width = 70
            leftThumb.requestLayout()


            rightThumb.layoutParams.width = LayoutParams.WRAP_CONTENT
            rightThumb.layoutParams.height = LayoutParams.WRAP_CONTENT

        }

        buttonCreate.setOnClickListener {
            checkValidation()
        }

        imageViewBack.setOnClickListener {
            commonMethod(LoginActivity::class.java)
        }

        checkBoxTerms.setOnCheckedChangeListener { _, isChecked ->
            isCheck = isChecked
        }
    }

    private fun checkValidation() = with(binding) {
        val name = textInputEditTextFirstName.text.toString().trim()
        val mobileNumber = textInputEditTextMobileNumber.text.toString().trim()
        val email = textInputEditTextEmail.text.toString().trim()
        val pass = textInputEditTextPassword.text.toString().trim()

        if (name.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_name))
            textInputEditTextFirstName.requestFocus()
        } else if (mobileNumber.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_mobile_number))
            textInputEditTextMobileNumber.requestFocus()
        } else if (mobileNumber.length != 10) {
            showMessage(getString(R.string.validation_please_enter_a_valid_mobile_number))
            textInputEditTextMobileNumber.requestFocus()
        } else if (email.isEmpty()) {
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
        } else if (!isCheck) {
            showMessage(getString(R.string.validation_please_check_terms_condition))
        } else {
            showMessage(getString(R.string.validation_signup_successfully))
            commonMethod(MainActivity::class.java)
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