package com.starter.app.ui.navbar.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.starter.app.R
import com.starter.app.databinding.ActivityNavigationBinding
import com.starter.app.ui.apicalldemo.adapter.GetStateAdapter
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.navbar.adaper.NavMenuAdapter
import com.starter.app.ui.navbar.pojo.NavMenuData
import com.starter.app.ui.productTask.fragment.ProductListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityNavigationBinding

    private val navManuListAdapter by lazy { GetStateAdapter() }
    private val navMenuAdapter by lazy { NavMenuAdapter() }

    private lateinit var toggle: ActionBarDrawerToggle
//    private val viewModel: NavMenuViewModel by viewModels()

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        load(ProductListFragment::class.java).replace(false)
        setTollBar()
        setAdapter()
//        apiCall()
//        observeLiveData()

        setClickListener()
        setActionBar()


    }

    private fun setActionBar() = with(binding) {
        toggle = ActionBarDrawerToggle(
            this@NavigationActivity,
            drawerLayout,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )

//        toggle.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_24)

        drawerLayout.addDrawerListener(object : DrawerListener {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val moveFactor: Float = drawerView.width * slideOffset
                val min = 0.1f
                val max = 1.0f
                val scaleFactor = max - min * slideOffset
                constraint.translationX = moveFactor
                constraint.scaleX = scaleFactor
                constraint.scaleY = scaleFactor
            }

            override fun onDrawerOpened(drawerView: View) {}

            override fun onDrawerClosed(drawerView: View) {}

            override fun onDrawerStateChanged(newState: Int) {}
        })

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setClickListener() {
        binding.navView.setNavigationItemSelectedListener(this)
    }
    private fun addData(): MutableList<NavMenuData> {
        return mutableListOf<NavMenuData>().apply {
            add(NavMenuData(R.drawable.baseline_home_24, getString(R.string.home)))
            add(NavMenuData(R.drawable.baseline_share_24, getString(R.string.share)))
            add(NavMenuData(R.drawable.baseline_info_24, getString(R.string.about_us)))
            add(NavMenuData(R.drawable.baseline_settings_24, getString(R.string.settings)))
            add(NavMenuData(R.drawable.baseline_exit_to_app_24, getString(R.string.logout)))
        }

    }

    private fun setAdapter() = with(binding) {
        recyclerView.apply {
            adapter = navMenuAdapter
            layoutManager =
                LinearLayoutManager(this@NavigationActivity, LinearLayoutManager.VERTICAL, false)

        }
        navMenuAdapter.addItem(addData())
    }

//    private fun observeLiveData() {
//        viewModel.navLiveData.observe(this, {
//            navManuAdapter.addItem(it.data)
//        })
//    }

//    private fun apiCall() {
//        viewModel.getMenuList()
//    }

    private fun setTollBar() {
        setToolbar(binding.toolbar)
        setToolbarElevation(true)
        setToolbarColor(R.color.blue)
        setToolbarTitle("ToolBar")
        showToolbar(true)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHome -> {
                showMessage(getString(R.string.home))
            }

            R.id.navSettings -> {
                showMessage(getString(R.string.settings))
            }

            R.id.navShare -> {
                showMessage(getString(R.string.share))
            }

            R.id.navAbout -> {
                showMessage(getString(R.string.about_us))
            }

            R.id.navLogout -> {
                showMessage(getString(R.string.logout))
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}