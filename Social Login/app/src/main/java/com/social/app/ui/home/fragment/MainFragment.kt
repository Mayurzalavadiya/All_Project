package com.social.app.ui.home.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.social.app.R
import com.social.app.core.AppPreferences
import com.social.app.core.Session
import com.social.app.data.pojo.User
import com.social.app.databinding.HomeFragmentMainBinding
import com.social.app.ui.activity.AuthActivity
import com.social.app.ui.activity.IsolatedActivity
import com.social.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<HomeFragmentMainBinding>() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var appPreferences: AppPreferences

    private var startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    activityResult.data?.let {
                        showMessage(it.getStringExtra("my_data") ?: "")
                    }
                } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                    //cancelled
                }
            })

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): HomeFragmentMainBinding {
        return HomeFragmentMainBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setFirebase()
        binding.buttonStart.setOnClickListener {
            logOut()
            /* session.user = User("1")

             navigator.loadActivity(IsolatedActivity::class.java, OrderFragment::class.java)
                 .forResult(startForResult)
                 .start()*/
        }
    }


    private fun logOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()
        }
    }

    private fun setFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }
}