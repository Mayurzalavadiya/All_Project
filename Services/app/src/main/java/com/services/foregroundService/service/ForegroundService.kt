package com.services.foregroundService.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat


class ForegroundService : Service() {

    private val TAG = "ForegroundService"
    private var count = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val CHANNEL_ID = "Random number notification"

    override fun onCreate() {
        startNotification()

        Log.e(TAG, "onCreate: ForegroundService")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ForegroundService")
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            count++
            Log.e(TAG, "onStartCommand: ForegroundService $count")
            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)


        return super.onStartCommand(intent, flags, startId)
    }


    /**
     * Used for creating and starting notification
     * whenever we start our Bound service
     */
    private fun startNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("A service is running in the background")
            .setContentText("Generating random number").build()
        startForeground(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind: ForegroundService")
        return null
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy: ForegroundService")
        if (::handler.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }

//    override fun onUnbind(intent: Intent?): Boolean {
//        Log.e(TAG, "onUnbind: ForegroundService")
//        return super.onUnbind(intent)
//    }
//
//    override fun onRebind(intent: Intent?) {
//        Log.e(TAG, "onRebind: ForegroundService")
//        super.onRebind(intent)
//    }
}
