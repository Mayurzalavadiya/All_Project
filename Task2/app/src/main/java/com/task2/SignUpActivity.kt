package com.task2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.task2.R.string.i_agree_to_terms_condition
import com.task2.databinding.ActivitySignUpBinding

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private var isCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        setTermsCondition()
    }

    private fun setTermsCondition() = with(binding) {

        val ss = SpannableString(getString(i_agree_to_terms_condition))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                CommonClass.showMessage(this@SignUpActivity, "Terms & Condition")

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = resources.getColor(R.color.orange)
            }
        }
        ss.setSpan(clickableSpan, 11, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewCheckTerms.text = ss
        textViewCheckTerms.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun setClickListener() = with(binding) {

        buttonCreate.setOnClickListener {
            checkValidation()
        }



        checkBoxTerms.setOnCheckedChangeListener { _, isChecked ->
            isCheck = isChecked
        }
    }

    private fun checkValidation() = with(binding) {
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val mobileNumber = editTextMobileNumber.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirmPassword.text.toString().trim()

        if (firstName.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_first_name))
            editTextFirstName.requestFocus()
        } else if (lastName.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_last_name))
            editTextLastName.requestFocus()
        } else if (email.isNotEmpty()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showMessage(getString(R.string.validation_please_enter_a_valid_email_address))
                editTextEmail.requestFocus()
            } else {

            }
        } else if (mobileNumber.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_mobile_number))
            editTextMobileNumber.requestFocus()
        } else if (mobileNumber.length != 10) {
            showMessage(getString(R.string.validation_please_enter_a_valid_mobile_number))
            editTextMobileNumber.requestFocus()
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
        } else if (!isCheck) {
            showMessage(getString(R.string.validation_please_check_terms_condition))
        } else {
            showMessage(getString(R.string.validation_signup_successfully))
            moveToNextPage(AddNewAddressActivity::class.java)
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