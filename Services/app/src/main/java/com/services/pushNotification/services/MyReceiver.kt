package com.services.pushNotification.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.services.utils.NotificationHelper


class MyReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper by lazy { NotificationHelper(context!!) }

        var message = intent?.getStringExtra(NotificationHelper.MESSAGE)
        if (message != null) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
            message = null

//            notificationHelper.cancle(NotificationHelper.CUSTOM_ID)

        }
    }
}
