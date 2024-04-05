package com.services.activity

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import com.permissionx.guolindev.PermissionX
import com.services.backgroundService.activity.BackGroundActivity
import com.services.boundService.activity.ActivityA
import com.services.databinding.ActivityMainBinding
import com.services.foregroundService.activity.ForeGroundActivity
import com.services.pushNotification.activity.NotificationActivity
import com.services.stepCounter.activity.StepCountActivity
import com.services.stopWatch.activity.StopWatchActivity
import com.services.utils.CustomDialogFragment
import com.services.workManager.activity.WorkManagerActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    var isPermission = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionCheck()
        setClickListener()
    }

    private fun permissionCheck() {
        PermissionX.init(this)
            .permissions(Manifest.permission.POST_NOTIFICATIONS)
            .setDialogTintColor(Color.parseColor("#1972e8"), Color.parseColor("#8ab6f5"))

            .onExplainRequestReason { scope, deniedList, beforeRequest ->
//                    val message = "PermissionX needs following permissions to continue"
//                    scope.showRequestReasonDialog(deniedList, message, "Allow", "Deny")
                val message = "Please allow the following permissions in settings"
                val dialog = CustomDialogFragment(message, deniedList)
                scope.showRequestReasonDialog(dialog)
            }
            .onForwardToSettings { scope, deniedList ->
                val message = "Please allow following permissions in settings"
                scope.showForwardToSettingsDialog(deniedList, message, "Allow", "Deny")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    isPermission = true
                } else {
                    showMessage(this, "The following permissions are denied：$deniedList")
                }
            }
    }

    private fun setClickListener() = with(binding) {
        buttonBackgroundService.setOnClickListener {
            if (isPermission)
                moveToNextScreen(
                    this@MainActivity,
                    BackGroundActivity::class.java
                ) else permissionCheck()
        }

        buttonForeGroundService.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, ForeGroundActivity::class.java)
            else
                permissionCheck()
        }
        buttonBoundService.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, ActivityA::class.java)
            else
                permissionCheck()
        }
        buttonNotification.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, NotificationActivity::class.java)
            else
                permissionCheck()
        }
        buttonStopWatch.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, StopWatchActivity::class.java)
            else
                permissionCheck()
        }
        buttonStepCounter.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, StepCountActivity::class.java)
            else
                permissionCheck()
        }
        buttonWorkManager.setOnClickListener {
            if (isPermission)
                moveToNextScreen(this@MainActivity, WorkManagerActivity::class.java)
            else
                permissionCheck()
        }
    }
}