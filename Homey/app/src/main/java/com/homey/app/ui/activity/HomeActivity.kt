package com.homey.app.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.databinding.HomeActivityBinding
import com.homey.app.di.DiConstants
import com.homey.app.ui.base.BaseActivity
import com.homey.app.ui.home.blogs.fragment.BlogFragment
import com.homey.app.ui.home.favorite.fragment.FavoriteFragment
import com.homey.app.ui.home.fragment.MainFragment
import com.homey.app.ui.home.myBooking.fragment.MyBookingFragment
import com.homey.app.ui.home.settings.fragment.SettingFragment
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    lateinit var binding: HomeActivityBinding


    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment()
        setClickListener()
    }

    private fun loadFragment() {
        load(MainFragment::class.java).replace(false)
    }

    private fun setClickListener() = with(binding) {

        bottomNavigation.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.bottomHome -> {
                        load(MainFragment::class.java).clearAll()
                            .replace(false)
                        return true
                    }

                    R.id.bottoMyBooking -> {
                        load(MyBookingFragment::class.java).replace(true, Keys.FRAGMENT_MY_BOOKING)
                        return true
                    }

                    R.id.bottomFavorites -> {
                        load(FavoriteFragment::class.java).replace(true, Keys.FRAGMENT_FAVORITE)
                        return true
                    }

                    R.id.bottomBlogs -> {
                        load(BlogFragment::class.java).replace(true, Keys.FRAGMENT_BLOG)
                        return true
                    }

                    R.id.bottomSettings -> {
                        load(SettingFragment::class.java).replace(true, Keys.FRAGMENT_SETTING)
                        return true
                    }
                    // Add more cases for other menu items if needed
                    else -> return false
                }
            }
        })
    }


    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
            val fragments: List<Fragment> = supportFragmentManager.fragments
            var selectedFragmentTag: String? = null

            for (fragment in fragments) {
                if (fragment.isVisible) {
                    selectedFragmentTag = fragment.tag
                    break
                }
            }

            selectedFragmentTag?.let { tag ->
                when (tag) {
                    Keys.FRAGMENT_MAIN -> binding.bottomNavigation.selectedItemId = R.id.bottomHome
                    Keys.FRAGMENT_MY_BOOKING -> binding.bottomNavigation.selectedItemId =
                        R.id.bottoMyBooking

                    Keys.FRAGMENT_FAVORITE -> binding.bottomNavigation.selectedItemId =
                        R.id.bottomFavorites

                    Keys.FRAGMENT_BLOG -> binding.bottomNavigation.selectedItemId = R.id.bottomBlogs
                    Keys.FRAGMENT_SETTING -> binding.bottomNavigation.selectedItemId =
                        R.id.bottomSettings
                }
            }
        } else {
            super.onBackPressed()
        }
    }


}
