package com.social.app.ui.auth.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.facebook.*
import com.facebook.login.BuildConfig
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.social.app.R
import com.social.app.databinding.AuthFragmentLoginBinding
import com.social.app.ui.activity.HomeActivity
import com.social.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthFragmentLoginBinding>() {


    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val reqCode: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth


    private lateinit var callbackManager: CallbackManager


    var id = ""
    var firstName = ""
    var middleName = ""
    var lastName = ""
    var name = ""
    var picture = ""
    var email = ""
    var accessToken = ""


    override fun bindData() {

        setupFirebase()
        setUpFacebook()
        setClickListener()

    }

    private fun setUpFacebook() {
        callbackManager = CallbackManager.Factory.create()

    }

    private fun isLoggedIn(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

    fun logOutUser() {
        LoginManager.getInstance().logOut()
    }

    fun openDetailsActivity() {
        navigator.loadActivity(HomeActivity::class.java).addBundle(Bundle().apply {
            putString("facebook_id", id)
            putString("facebook_first_name", firstName)
            putString("facebook_middle_name", middleName)
            putString("facebook_last_name", lastName)
            putString("facebook_name", name)
            putString("facebook_picture", picture)
            putString("facebook_email", email)
            putString("facebook_access_token", accessToken)
        }).byFinishingAll().start()
    }

    private fun setupFirebase() {
        FirebaseApp.initializeApp(requireActivity())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()


    }


    private fun setClickListener() = with(binding) {
        btnGoogle.setOnClickListener {
            signInGoogle()
        }

        btnFacebook.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(requireActivity(), listOf("public_profile", "email"))

            LoginManager.getInstance()
                .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        Log.d("TAG", "Success Login")
//                        handleFacebookAccessToken(result.accessToken)
                        getUserProfile(result.accessToken, result.accessToken.userId)
                    }

                    override fun onCancel() {
                        // Handle login cancellation
                        showMessage("Login cancelled")
                    }

                    override fun onError(error: FacebookException) {
                        // Handle login error
                        showMessage("Login error: ${error.message}")
                    }
                })
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
//                    updateUI(user)
                    Log.d("TAG", "${user}:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)

//                    updateUI(null)
                }
            }
    }
    fun getUserProfile(token: AccessToken?, userId: String?) {

        val parameters = Bundle()
        parameters.putString(
            "fields",
            "id, first_name, middle_name, last_name, name, picture, email"
        )
        GraphRequest(token,
            "/$userId/",
            parameters,
            HttpMethod.GET,
            GraphRequest.Callback { response ->
                val jsonObject = response.jsonObject ?: return@Callback

                // Facebook Access Token
                // You can see Access Token only in Debug mode.
                // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
                if (BuildConfig.DEBUG) {
                    FacebookSdk.setIsDebugEnabled(true)
                    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
                }
                accessToken = token.toString()

                // Facebook Id
                id = if (jsonObject.has("id")) {
                    val facebookId = jsonObject.getString("id")
                    Log.i("Facebook Id: ", facebookId.toString())
                    facebookId.toString()
                } else {
                    Log.i("Facebook Id: ", "Not exists")
                    "Not exists"
                }


                // Facebook First Name
                firstName = if (jsonObject.has("first_name")) {
                    val facebookFirstName = jsonObject.getString("first_name")
                    Log.i("Facebook First Name: ", facebookFirstName)
                    facebookFirstName
                } else {
                    Log.i("Facebook First Name: ", "Not exists")
                    "Not exists"
                }


                // Facebook Middle Name
                middleName = if (jsonObject.has("middle_name")) {
                    val facebookMiddleName = jsonObject.getString("middle_name")
                    Log.i("Facebook Middle Name: ", facebookMiddleName)
                    facebookMiddleName
                } else {
                    Log.i("Facebook Middle Name: ", "Not exists")
                    "Not exists"
                }


                // Facebook Last Name
                lastName = if (jsonObject.has("last_name")) {
                    val facebookLastName = jsonObject.getString("last_name")
                    Log.i("Facebook Last Name: ", facebookLastName)
                    facebookLastName
                } else {
                    Log.i("Facebook Last Name: ", "Not exists")
                    "Not exists"
                }


                // Facebook Name
                name = if (jsonObject.has("name")) {
                    val facebookName = jsonObject.getString("name")
                    Log.i("Facebook Name: ", facebookName)
                    facebookName
                } else {
                    Log.i("Facebook Name: ", "Not exists")
                    "Not exists"
                }


                // Facebook Profile Pic URL
                if (jsonObject.has("picture")) {
                    val facebookPictureObject = jsonObject.getJSONObject("picture")
                    if (facebookPictureObject.has("data")) {
                        val facebookDataObject = facebookPictureObject.getJSONObject("data")
                        if (facebookDataObject.has("url")) {
                            val facebookProfilePicURL = facebookDataObject.getString("url")
                            Log.i("Facebook Profile Pic URL: ", facebookProfilePicURL)
                            picture = facebookProfilePicURL
                        }
                    }
                } else {
                    Log.i("Facebook Profile Pic URL: ", "Not exists")
                    picture = "Not exists"
                }

                // Facebook Email
                email = if (jsonObject.has("email")) {
                    val facebookEmail = jsonObject.getString("email")
                    Log.i("Facebook Email: ", facebookEmail)
                    facebookEmail
                } else {
                    Log.i("Facebook Email: ", "Not exists")
                    "Not exists"
                }

//                openDetailsActivity()
            }).executeAsync()
    }




/*    private fun generateHashKey() {
        try {
            val info: PackageInfo =
                requireActivity().packageManager.getPackageInfo(
                    requireActivity().packageName,
                    PackageManager.GET_SIGNATURES
                )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("TAG", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("TAG", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("TAG", "printHashKey()", e)
        }
    }*/


    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, reqCode)
    }


    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): AuthFragmentLoginBinding {
        return AuthFragmentLoginBinding.inflate(inflater, container, attachToRoot)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

          when (requestCode) {
              reqCode -> {
                  val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                  if (task.isSuccessful) {
                      handleSignInResult(task)
                  }
              }

              else -> {
        callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
            Log.w("TAG", "signInResult:success code=")
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                account?.let {
                    Log.e(
                        "TAG",
                        "updateUI: ${it.displayName} ${it.account} ${it.givenName.toString()} ${it.familyName.toString()} ${it.email.toString()} ${it.id.toString()} ${it.photoUrl.toString()}"
                    )
                }
                navigator.load(SignupFragment::class.java).replace(false)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(requireActivity()) != null) {
            navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
        } else if (isLoggedIn()) {
            navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
        }
    }
}