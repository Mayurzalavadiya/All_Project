package com.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.task3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupFirebase()
        setClickListener()

    }

    private fun setupFirebase() {
        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()


    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    private fun setClickListener() = with(binding) {
        buttonSignIn.setOnClickListener {
            checkValidation()
        }
        buttonmaterial.setOnClickListener {
            moveToNextPage(MaterialActivity::class.java)
        }

        imageViewGoogle.setOnClickListener { view: View? ->
            Toast.makeText(this@LoginActivity, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, DashBoardActivity::class.java)
                intent.apply {
                    putExtra("DNAME", account.displayName.toString())
                    putExtra("NAME", account.givenName.toString())
                    putExtra("FNAME", account.familyName.toString())
                    putExtra("EMAIL", account.email.toString())
                    putExtra("ID", account.id.toString())
                    putExtra("IMAGE", account.photoUrl.toString())
                }
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(
                Intent(
                    this, DashBoardActivity
                    ::class.java
                )
            )
            finish()
        }
    }

    private fun checkValidation() = with(binding) {
        val email = editTextEmail.text.toString().trim()
        val pass = editTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_an_email_address))
            editTextEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(getString(R.string.validation_please_enter_a_valid_email_address))
            editTextEmail.requestFocus()
        } else if (pass.isEmpty()) {
            showMessage(getString(R.string.validation_please_enter_a_password))
            editTextPassword.requestFocus()
        } else if (pass.length < 6) {
            showMessage(getString(R.string.validation_password_length_must_be_6_digits))
            editTextPassword.requestFocus()
        } else {
            showMessage(getString(R.string.validation_sign_in_successfully))
            moveToNextPage(MainActivity::class.java)
            finish()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToNextPage(nextClass: Class<*>, isFinish: Boolean = false) {
        val intent = Intent(this, nextClass)
        startActivity(intent)

        if (isFinish) {
            finish()
        }
    }
}