package com.social.app.ui.auth.fragment

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.social.app.R
import com.social.app.databinding.AuthFragmentSignupBinding
import com.social.app.ui.activity.AuthActivity
import com.social.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.Locale

@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {


    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var dictionary: List<String> = emptyList()


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun bindData() {
        setFirebase()
        binding.editText.requestFocus()
        showKeyBoard()
        setClickListener()
        CoroutineScope(Dispatchers.IO).launch {
            loadDictionary()
        }

    }


    private fun logOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()
        }
//        LoginManager.getInstance().logOut()
    }

    private fun setFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun setClickListener() {
        binding.buttonCheck.setOnClickListener {
            val text = binding.editText.text.toString()

            if (text.isNotEmpty()) {
                dictionary.let { dict ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val correctedText = correctSpelling(text, dict)
                        withContext(Dispatchers.Main) {
                            binding.editText.setText(correctedText)
                            binding.editText.setSelection(correctedText.length)
                        }
                    }
                }
            } else {
                showMessage("Please enter text to check")
            }
        }

        binding.buttonLogout.setOnClickListener {
            logOut()
        }
    }

    fun loadDictionary() {
        try {
            val url = URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt")
            val inputStream = url.openStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val list = bufferedReader.useLines { lines ->
                lines.map { it.trim() }.toList()
            }
            dictionary = list
        } catch (e: Exception) {
            println("Error loading dictionary: ${e.message}")
            dictionary = emptyList() // or null, depending on your error handling strategy
        }
    }



    private fun correctSpelling(text: String, dictionary: List<String>): String {
        return text.split("\\s+".toRegex())
            .map { word ->
                if (word.isNotEmpty() && word.toLowerCase(Locale.ROOT) !in dictionary) {
                    findClosestWord(word.toLowerCase(Locale.ROOT), dictionary)
                } else {
                    word
                }
            }
            .joinToString(" ")
    }

    private fun findClosestWord(word: String, dictionary: List<String>): String {
        var closestWord = ""
        var minDistance = Int.MAX_VALUE
        for (dictWord in dictionary) {
            val distance = levenshteinDistance(word, dictWord)
            if (distance < minDistance) {
                minDistance = distance
                closestWord = dictWord
            }
        }
        return closestWord
    }

    private fun levenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) { 0 } }

        for (i in 0..s1.length) {
            for (j in 0..s2.length) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    s1[i - 1] == s2[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                    else -> dp[i][j] =
                        1 + minOf(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1])
                }
            }
        }

        return dp[s1.length][s2.length]
    }
}
