package com.services.workManager.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.services.utils.NotificationHelper
import com.services.workManager.MyApp
import com.services.workManager.User
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlinx.coroutines.withContext

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val notificationHelper by lazy { NotificationHelper(context) }
    private val userDao = MyApp.database.userDao()

    override suspend fun doWork(): Result {

        val data = inputData.getString(DATA)

        when (data) {
            ONE_TIME -> {
                oneTimeNotification()
            }

            DRINK_WATER -> {
                setData(DRINK_WATER)
                periodicNotification()

            }

            EAT_FOOD -> {

                setData(EAT_FOOD)
                eatNotification()
            }
        }
        return Result.success()
    }

    private suspend fun setData(type: String) {
        val user = User(type = type, time = getCurrentTime("HH:mm:ss"))
        userDao.insertUser(user)
    }

    private fun oneTimeNotification() {
        val simpleNotification = notificationHelper.createNotification(
            "Simple WorkManager", "This is a Work Manager."
        )
        notificationHelper.notify(0, simpleNotification)
    }

    private fun periodicNotification() {
        val simpleNotification = notificationHelper.createNotification(
            "Periodic WorkManager", DRINK_WATER
        )
        notificationHelper.notify(2, simpleNotification)

    }

    private fun eatNotification() {
        val simpleNotification = notificationHelper.createNotification(
            "Periodic WorkManager", EAT_FOOD
        )
        notificationHelper.notify(5, simpleNotification)

    }

    companion object {
        const val DATA = "data"
        const val ONE_TIME = "one_time"
        const val DRINK_WATER = "Drink Water"
        const val EAT_FOOD = "Eat Food"
    }

    private fun getCurrentTime(formate: String): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(formate)
        return dateFormat.format(calendar.time)
    }
}
