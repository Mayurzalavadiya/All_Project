package com.starter.app.ui.bottomBar

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.starter.app.R
import com.starter.app.databinding.ActivityBottomBarBinding
import com.starter.app.ui.base.BaseActivity


class BottomBarActivity : BaseActivity() {

    private lateinit var binding: ActivityBottomBarBinding


    override fun findFragmentPlaceHolder(): Int = 0

    override fun createViewBinding(): View {
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClickListener()
    }

    private fun setClickListener() {
        binding.fab.setOnClickListener {
            showMessage("FAB Clicked")
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.bottomHome -> {
                    showMessage("Home Clicked")
                    true
                }

                R.id.bottomShare -> {
                    showMessage("Share Clicked")
                    true
                }

                R.id.bottomAboutUs -> {
                    showMessage("About us Clicked")
                    true
                }

                else -> false
            }
        }

    }
}