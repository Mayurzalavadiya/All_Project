package com.services.workManager.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.services.databinding.ActivityWorkManagerBinding
import com.services.workManager.service.MyWorker
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setClickListener()

    }

    private fun setClickListener() = with(binding) {
        val uploadDataConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        buttonOneTimeRequest.setOnClickListener {


            val inputData = Data.Builder()
                .putString(MyWorker.DATA, MyWorker.ONE_TIME)
                .build()

            val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(uploadDataConstraints)
                .setInputData(inputData)
                .build()

            WorkManager.getInstance(this@WorkManagerActivity).enqueue(oneTimeWorkRequest)

        }

        buttonPeriodicalRequest.setOnClickListener {

            val inputData = Data.Builder()
                .putString(MyWorker.DATA, MyWorker.PERIODIC_TIME)
                .build()


            val periodicWorkRequest =
                PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
                    .setConstraints(uploadDataConstraints)
                    .setInputData(inputData)
                    .build()

            WorkManager.getInstance(this@WorkManagerActivity).enqueue(periodicWorkRequest)
        }
    }
}

