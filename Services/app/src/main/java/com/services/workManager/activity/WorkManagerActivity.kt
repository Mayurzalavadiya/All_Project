package com.services.workManager.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.services.R
import com.services.databinding.ActivityWorkManagerBinding
import com.services.workManager.MyApp
import com.services.workManager.adapter.NotificationAdapter
import com.services.workManager.service.MyWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding
    private lateinit var notificationAdapter: NotificationAdapter

    private val userDao = MyApp.database.userDao()

    var drinkTime: Int? = null
    var eatTime: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpinner()
        setUpAdapter()
        setClickListener()

    }

    fun getTime(): ArrayList<Int> {
        return ArrayList<Int>().apply {
            for (i in 1..60) {
                add(i)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchDataAndUpdateUI()
    }

    private fun setSpinner() = with(binding) {

        val timeList = getTime()

        val adapter = ArrayAdapter(
            this@WorkManagerActivity,
            R.layout.custom_spinner_item,
            timeList
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDrink.adapter = adapter
        spinnerEat.adapter = adapter

        spinnerDrink.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                drinkTime = getTime()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        spinnerEat.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    eatTime = getTime()[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
    }

    private fun setUpAdapter() {
        binding.recyclerView.apply {
            notificationAdapter = NotificationAdapter()
            layoutManager = LinearLayoutManager(
                this@WorkManagerActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = notificationAdapter
            notificationAdapter.callback(::deleteDataAndUpdateUI)
        }
    }

    private fun setClickListener() {

        binding.buttonOneTimeRequest.setOnClickListener {
            val uploadDataConstraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            val inputData = Data.Builder().putString(MyWorker.DATA, MyWorker.ONE_TIME).build()
            val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(uploadDataConstraints)
                .setInputData(inputData)
                .build()
            WorkManager.getInstance(this@WorkManagerActivity).enqueue(oneTimeWorkRequest)
        }

        binding.buttonPeriodicalRequest.setOnClickListener {

            Log.e("TAG", "setClickListener: $drinkTime  $eatTime")

            val uploadDataConstraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

//            WorkManager.getInstance(this@WorkManagerActivity).cancelAllWorkByTag("periodicWork")

            drinkTime?.let {
                val inputData =
                    Data.Builder().putString(MyWorker.DATA, MyWorker.DRINK_WATER).build()
                val periodicWorkRequestDrink =
                    PeriodicWorkRequestBuilder<MyWorker>(it.toLong(), TimeUnit.MINUTES)
                        .setConstraints(uploadDataConstraints)
                        .setInputData(inputData)
                        .addTag("drink_notification")
                        .build()
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(periodicWorkRequestDrink)
            }


            eatTime?.let {
                val inputData1 = Data.Builder().putString(MyWorker.DATA, MyWorker.EAT_FOOD).build()
                val periodicWorkRequestEat =
                    PeriodicWorkRequestBuilder<MyWorker>(it.toLong(), TimeUnit.MINUTES)
                        .setConstraints(uploadDataConstraints)
                        .setInputData(inputData1)
                        .addTag("eat_notification")
                        .build()
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(periodicWorkRequestEat)

            }

//            fetchDataAndUpdateUI()
            Handler(Looper.getMainLooper()).postDelayed(

                Runnable { fetchDataAndUpdateUI() }, 1000
            )

        }
    }

    private fun fetchDataAndUpdateUI() {
        Log.e("TAG", "fetchDataAndUpdateUI: ")
        GlobalScope.launch(Dispatchers.IO) {
            val users = userDao.getAllUsers()
            withContext(Dispatchers.Main) {
                notificationAdapter.addItem(users) // Update UI on the main thread
            }
        }
    }

    private fun deleteDataAndUpdateUI(position: Int) {
        Log.e("TAG", "deleteDataAndUpdateUI: $position")
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
//                userDao.deleteUser(position.toLong())
                fetchDataAndUpdateUI()
            }
        }
    }


}
