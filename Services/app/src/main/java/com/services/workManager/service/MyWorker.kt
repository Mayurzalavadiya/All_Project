package com.services.workManager.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.services.pushNotification.activity.NotificationHelper

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val notificationHelper by lazy { NotificationHelper(context) }


    override fun doWork(): Result {

        Log.e("TAG", "doWork: it's success")
        val data = inputData.getString(DATA)

        if (data.equals(ONE_TIME)) {
            oneTimeNotification()
        } else {
            periodicNotification()
        }
        return Result.success()
    }

    private fun oneTimeNotification() {
        val simpleNotification = notificationHelper.createNotification(
            "Simple WorkManager",
            "This is a Work Manager."
        )
        notificationHelper.notify(0, simpleNotification)
    }

    private fun periodicNotification() {
        val simpleNotification = notificationHelper.createNotification(
            "Periodic WorkManager",
            "This is a periodic Manager."
        )
        notificationHelper.notify(2, simpleNotification)
    }

    companion object {
        const val DATA = "data"
        const val ONE_TIME = "one_time"
        const val PERIODIC_TIME = "periodic_time"
    }

}
