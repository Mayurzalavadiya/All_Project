package com.constraint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var loginTextView: TextView? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var forgotPasswordTextView: TextView? = null
    private var loginButton: Button? = null
    private var createNewAccountTextView: TextView? = null
    private var orTextView: TextView? = null
    private var githubImageView: ImageView? = null
    private var twitterImageView: ImageView? = null
    private var facebookImageView: ImageView? = null
    private var googleImageView: ImageView? = null
    private var phoneImageView: ImageView? = null

    private val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        loginTextView = findViewById(R.id.login)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        forgotPasswordTextView = findViewById(R.id.forgotpassword)
        loginButton = findViewById(R.id.btn_login)
        createNewAccountTextView = findViewById(R.id.createnweaccount)
        orTextView = findViewById(R.id.textView4)
        githubImageView = findViewById(R.id.btn_github)
        twitterImageView = findViewById(R.id.btn_twitter)
        facebookImageView = findViewById(R.id.btn_facebook)
        googleImageView = findViewById(R.id.btn_google)
        phoneImageView = findViewById(R.id.btn_phone)

        loginButton!!.setOnClickListener {
            // Handle login button click
            val email = emailEditText!!.text.toString().trim()
            val password = passwordEditText!!.text.toString().trim()
            // You can add your login logic here
            val dummyEmail = ""
            val dummyPassword = ""

            if (email == dummyEmail && password == dummyPassword) {
                // Successful login
                val intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
                finishAffinity()
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            } else {
                // Failed login
                Toast.makeText(this, "Invalid credentials. Please try again", Toast.LENGTH_SHORT)
                    .show()

            }
        }

        createNewAccountTextView!!.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}
