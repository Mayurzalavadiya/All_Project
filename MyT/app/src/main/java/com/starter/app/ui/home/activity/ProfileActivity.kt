package com.starter.app.ui.home.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import com.starter.app.R
import com.starter.app.core.AppPreferences
import com.starter.app.databinding.ActivityProfileBinding
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.home.fragment.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemUiVisibilityLightTheme()
        load(ProfileFragment::class.java).replace(false)
    }

    private fun setSystemUiVisibilityLightTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val window = window
            val controller = window.insetsController
            //set statusBar color
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            // Set the appearance to light theme
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }
}