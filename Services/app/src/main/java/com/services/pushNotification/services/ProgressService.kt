package com.services.pushNotification.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.services.R
import com.services.activity.BaseActivity
import com.services.pushNotification.activity.NotificationActivity
import com.services.utils.NotificationHelper

class ProgressService : Service() {

    private val notificationHelper by lazy { NotificationHelper(this) }

    private var isAlreadyDoing = true

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (isAlreadyDoing) {
            isAlreadyDoing = false
            Handler(Looper.getMainLooper()).postDelayed({
                showProgressNotification()
            }, 1000)
        }
        else{
            BaseActivity().showMessage(this, getString(R.string.already_running_process))
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    private fun showProgressNotification() {
        val builder = NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
            .setContentTitle("Progress Notification")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setContentText("Task in progress...")
            .setColorized(true)
            .setColor(Color.parseColor("#FF018786"))
            .setSmallIcon(R.drawable.ic_dialog_info)
            .setProgress(100, 0, false)

        val notificationIntent = Intent(this, NotificationActivity::class.java)
        val pendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                0, notificationIntent, PendingIntent.FLAG_MUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        builder.setContentIntent(pendingIntent)
        val notification = builder.build()
        notificationHelper.notify(2, notification)

        val handler = Handler(Looper.getMainLooper())
        var progress = 0

        val progressRunnable = object : Runnable {
            override fun run() {
                if (progress < 100) {
                    builder.setContentText("$progress% Task complete")
                        .setProgress(100, progress, false)
                    notificationHelper.notify(2, builder.build())

                    handler.postDelayed(this, 500)
                    progress++
                } else {
                    isAlreadyDoing = true
                    builder.setContentText("Task completed")
                        .setProgress(0, 0, false)
                    notificationHelper.notify(2, builder.build())
                }
            }
        }

        handler.postDelayed(progressRunnable, 200)
    }

}