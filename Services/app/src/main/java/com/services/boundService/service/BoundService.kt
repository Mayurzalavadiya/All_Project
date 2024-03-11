package com.services.boundService.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat

class BoundService : Service() {

    private val TAG = BoundService::class.java.toString()
    private var count = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val CHANNEL_ID = "com.demo.notification"

    override fun onCreate() {
        Log.e(TAG, "onCreate: BoundService")
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            count++
            Log.e(TAG, "onStartCommand: BoundService $count")
            handler.postDelayed(runnable, 3000)
        }
        handler.postDelayed(runnable, 5000)
        Log.e(TAG, "onCreate: ", )
        startNotification()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: BoundService")
        return super.onStartCommand(intent, flags, startId)
    }

    private val binder: MyBinder = MyBinder()
    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "onBind: BoundService")
        return binder
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy: BoundService")
        if (::handler.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()

    }

    inner class MyBinder : Binder() {
        val service: BoundService
            get() = this@BoundService
    }

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
        Log.e(TAG, "startNotification: ")
        startForeground(1, notification)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind: BoundService")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.e(TAG, "onRebind: BoundService")
        super.onRebind(intent)
    }


    fun getCount(): Int = count

}
