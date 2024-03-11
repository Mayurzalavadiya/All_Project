package com.example.project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private var buttonLogIn: AppCompatButton? = null
    private var buttonSignUp: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonLogIn = findViewById(R.id.buttonLogIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonLogIn?.setOnClickListener {
            CommonMethod.moveToNextScreen(this, LoginActivity::class.java)
        }
        buttonSignUp?.setOnClickListener {
            CommonMethod.moveToNextScreen(this, SignUpActivity::class.java)
        }
    }
}