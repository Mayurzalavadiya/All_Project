package com.services.pushNotification.activity

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateUtils
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.services.R
import com.services.databinding.ActivityNotificationBinding
import com.services.pushNotification.services.MyReceiver
import com.services.pushNotification.services.ProgressService
import com.services.stepCounter.activity.StepCountActivity
import com.services.utils.NotificationHelper


class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    private val notificationHelper by lazy { NotificationHelper(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setClickListener()

    }


    private fun setClickListener() = with(binding) {

        buttonSimpleNotification.setOnClickListener {
            val simpleNotification = notificationHelper.createNotification(
                "Simple Notification",
                "This is a simple push notification."
            )
            notificationHelper.notify(1, simpleNotification)

        }

        buttonProgressNotification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(
                    Intent(
                        this@NotificationActivity,
                        ProgressService::class.java
                    )
                )
            }
        }

        buttonReDirectNotification.setOnClickListener {
            addNotification()
        }

        buttonCustomNotification.setOnClickListener {
//            setCustomNotification()
            sendNotification()
        }
    }

    private fun sendNotification() {
        val expandedView = RemoteViews(packageName, R.layout.notification_blog4_expanded)
        val collapsedView = RemoteViews(packageName, R.layout.notification_blog3_collapsed)

        expandedView.setImageViewResource(R.id.big_icon, R.drawable.ic_dialog_info)
        expandedView.setImageViewResource(R.id.small_icon, R.drawable.ic_notifications_teal_700)
        expandedView.setTextViewText(
            R.id.timestamp,
            DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
        )
        expandedView.setTextViewText(R.id.notification_message, "Notification")

        collapsedView.setImageViewResource(R.id.big_icon, R.drawable.circle)
        collapsedView.setImageViewResource(R.id.imageviewSmallIcon, R.drawable.ic_notifications_red)
        collapsedView.setTextViewText(
            R.id.timestamp,
            DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
        )

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

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
        //Left Button
        val leftIntent = Intent(this, MyReceiver::class.java).apply {
            putExtra(NotificationHelper.MESSAGE, "You clicked the left button")
        }
        val pendingLeftIntent = PendingIntent.getBroadcast(
            this,
            0,
            leftIntent,
            flag
        )
        expandedView.setOnClickPendingIntent(
            R.id.buttonLeft,
            pendingLeftIntent
        )

        //RightButton
        val rightIntent = Intent(this, MyReceiver::class.java).apply {
            putExtra(NotificationHelper.MESSAGE, "You clicked the right button")
        }
        val pendingRightIntent = PendingIntent.getBroadcast(
            this,
            1,
            rightIntent,
            flag
        )

        expandedView.setOnClickPendingIntent(
            R.id.buttonRight,
            pendingRightIntent
        )

        val customNotification = NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("NOTIFICATION_TITLE")
            .setContentText("Custom Notification")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // tapping notification will open MainActivity // setting the custom collapsed and expanded views
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView) // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        notificationHelper.notify(NotificationHelper.CUSTOM_ID, customNotification.build())
    }


    private fun addNotification() {
        val builder = NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_dialog_info) //set icon for notification
            .setContentTitle("Notifications Example") //set title of notification
            .setContentText("This is a notification message") //this is notification message
            .setAutoCancel(false) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification

        val notificationIntent = Intent(this, StepCountActivity::class.java)


//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a redirect by notification")

        val pendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            notificationHelper.cancle(3)

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


        // Add as notification
        notificationHelper.notify(3, builder.build())

    }

}
