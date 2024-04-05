package com.homey.app.utils

import android.app.Activity
import android.os.Build
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

fun titleBar(activity: Activity, color: Int, isWhiteTheme: Boolean = false) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val window = activity.window
        val controller = window.insetsController
        //set statusBar color
        window.statusBarColor = ContextCompat.getColor(activity, color)
        // Set the appearance to light theme
        if (isWhiteTheme) {
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }
}