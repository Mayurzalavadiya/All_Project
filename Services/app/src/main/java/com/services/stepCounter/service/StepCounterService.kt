package com.services.stepCounter.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.services.R
import com.services.stepCounter.activity.StepCountActivity
import com.services.stopWatch.service.StopwatchService

class StepCounterService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var totalSteps = 0f
    private var stepSensor: Sensor? = null
    private lateinit var notificationManager: NotificationManager

    companion object {
        const val STEP_COUNT_UPDATE_ACTION = "step_count_update_action"
        const val FOREGROUND_SERVICE_ID = 1
        var isServiceRunning = false

    }

    override fun onCreate() {
        super.onCreate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            stopSelf()
        }

        isServiceRunning = true

        // Start the service as a foreground service
        startForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createChannel()
        getNotificationManager()
        updateNotification()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                totalSteps = event.values[0]
                Log.e("StepCounterService", "Current Steps: $totalSteps")

                updateNotification()
                updateStepsCount(totalSteps)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not implemented for this example
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                StopwatchService.CHANNEL_ID,
                "Stopwatch",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.setSound(null, null)
            notificationChannel.setShowBadge(true)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationManager() {
        notificationManager = ContextCompat.getSystemService(
            this,
            NotificationManager::class.java
        ) as NotificationManager
    }

    private fun buildNotification(): Notification {
        val notificationIntent = Intent(this, StepCountActivity::class.java)
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

        return NotificationCompat.Builder(this, StopwatchService.CHANNEL_ID)
            .setContentTitle("Step Counter Service")
            .setContentText("Counting Steps: $totalSteps")
            .setColorized(true)
            .setColor(Color.parseColor("#FF018786"))
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification() {
        notificationManager.notify(FOREGROUND_SERVICE_ID, buildNotification())
    }

    private fun updateStepsCount(steps: Float) {
        val intent = Intent(STEP_COUNT_UPDATE_ACTION)
        intent.putExtra("steps", steps)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun startForegroundService() {
        val notification = buildNotification()
        startForeground(FOREGROUND_SERVICE_ID, notification)
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        isServiceRunning = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}