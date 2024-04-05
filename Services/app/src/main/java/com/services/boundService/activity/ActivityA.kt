package com.services.boundService.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.services.activity.BaseActivity
import com.services.boundService.service.BoundService
import com.services.databinding.ActivityABinding

class ActivityA : BaseActivity() {

    private lateinit var binding: ActivityABinding

    val TAG = ActivityA::class.java.toString()

    var mIsBound = false
    private lateinit var mService: BoundService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        buttonStartServices.setOnClickListener {
            val intent = Intent(this@ActivityA, BoundService::class.java)

            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        buttonStopServices.setOnClickListener {
            if (mIsBound) {
                unbindService(serviceConnection)
                mIsBound = false
            }
        }

        buttonGetCount.setOnClickListener {
            if (mIsBound && ::mService.isInitialized) {
                showMessage(this@ActivityA, mService.getCount().toString())
            }
        }

        buttonNextActivity.setOnClickListener {
            moveToNextScreen(this@ActivityA, ActivityB::class.java)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
            Log.e(TAG, "ServiceConnection: connected to service.")
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            val binder = iBinder as BoundService.MyBinder
            mService = binder.service
            mIsBound = true
//            getRandomNumberFromService() // return a random number from the service
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.e(TAG, "ServiceConnection: disconnected from service.")
            mIsBound = false
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        if (mIsBound) {
//            unbindService(serviceConnection)
//            mIsBound = false
//        }
//    }
}
