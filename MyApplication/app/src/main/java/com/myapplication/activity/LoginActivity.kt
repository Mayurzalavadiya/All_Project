package com.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import com.myapplication.util.CommonMethodClass

class LoginActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (isInputValid(firstName, lastName, password)) {
                val intent = Intent(this, Sqlite_Activity::class.java)
                CommonMethodClass(this,"Login Successful")
                startActivity(intent)
                finish()
            } else {
                CommonMethodClass(this,"Invalid Input. Please check your details.")
            }
        }
    }

    private fun isInputValid(firstName: String, lastName: String, password: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty()
    }

    private fun displayMessage(message: String) {
        // Replace this with your preferred way of displaying messages (e.g., Toast or Snackbar)
        println(message)
    }
}
