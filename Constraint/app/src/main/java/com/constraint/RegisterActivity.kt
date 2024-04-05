package com.constraint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private var register: TextView? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var conform_password: EditText? = null
    private var btn_register: Button? = null
    private var alreadygiveaccount: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register = findViewById(R.id.register)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        conform_password = findViewById(R.id.conform_password)
        btn_register = findViewById(R.id.btn_register)
        alreadygiveaccount = findViewById(R.id.alreadygiveaccount)

        btn_register!!.setOnClickListener {
            // Handle registration logic here
            val email = emailEditText!!.text.toString()
            val password = passwordEditText!!.text.toString()
            val confirmPassword = passwordEditText!!.text.toString()

            // Perform some basic validation
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT)
                    .show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        alreadygiveaccount!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}