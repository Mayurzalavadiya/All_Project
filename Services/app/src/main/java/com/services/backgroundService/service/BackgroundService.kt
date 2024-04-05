package com.services.backgroundService.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class BackgroundService : Service() {

    private val TAG = BackgroundService::class.java.toString()
    private var count = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate() {
        Log.e(TAG, "onCreate: BackgroundService ")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: BackgroundService")
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            count++
            Log.e(TAG, "onStartCommand: BackgroundService $count")
            handler.postDelayed(runnable, 1000)

//            if (count == 50) {
//                stopSelf()
//            }
        }
        handler.postDelayed(runnable, 1000)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.e(TAG, "onBind: BackgroundService")
        return null
    }

//    override fun onUnbind(intent: Intent?): Boolean {
//        Log.e(TAG, "onUnbind: BackgroundService")
//        return super.onUnbind(intent)
//    }
//
//    override fun onRebind(intent: Intent?) {
//        Log.e(TAG, "onRebind: BackgroundService" )
//        super.onRebind(intent)
//    }
    override fun onDestroy() {
        Log.e(TAG, "onDestroy: BackgroundService")
        if (::handler.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()

    }
}