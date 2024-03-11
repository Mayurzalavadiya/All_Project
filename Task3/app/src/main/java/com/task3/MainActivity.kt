package com.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.task3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }



    private fun setClickListener() = with((binding)){
        buttonLogin.setOnClickListener{
            moveToNextPage(LoginActivity::class.java)
        }

        buttonRegister.setOnClickListener{
            moveToNextPage(RegisterActivity::class.java)
        }

    }
    private fun moveToNextPage(nextClass: Class<*>) {
        val intent = Intent(this, nextClass)
        startActivity(intent)
    }
}