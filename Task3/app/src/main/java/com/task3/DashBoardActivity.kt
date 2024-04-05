package com.task3

import android.content.Intent
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.EventLogTags.Description
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.task3.databinding.ActivityDashBoardBinding


class DashBoardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashBoardBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        setFirebase()
        setClickListener()
    }

    private fun setData() = with(binding) {
        val data: String? =
            "${intent.getStringExtra("DNAME")}\n" +
                    "${intent.getStringExtra("NAME")}\n" +
                    "${intent.getStringExtra("FNAME")}\n" +
                    "${intent.getStringExtra("EMAIL")}\n" +
                    "${intent.getStringExtra("ID")}"

        textView.text = data
        Log.e("TAG", "setData: ${intent.getStringExtra("IMAGE")}", )
        Glide.with(this@DashBoardActivity).load(intent.getStringExtra("IMAGE")).into(imageView)
    }

    private fun setClickListener() = with(binding) {
        buttonLogOut.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this@DashBoardActivity, LoginActivity::class.java)
                Toast.makeText(this@DashBoardActivity, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

}