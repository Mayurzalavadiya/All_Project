package com.example.coroutines.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineActivity : AppCompatActivity() {

    private val TAG = CoroutineActivity::class.java.toString()

    private lateinit var binding: ActivityCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonCoroutine.setOnClickListener {
            increment()
            Log.e(TAG, "setClickListener: process Done")
        }

        buttonGlobalScope.setOnClickListener { globalScopeDemo() }
        buttonLifecycleScope.setOnClickListener { lifeCycleScope() }
        buttonLaunchBuilder.setOnClickListener { launchBuilder() }
        buttonAsyncBuilder.setOnClickListener { asyncBuilder() }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun asyncBuilder() {
        println("Start")

        val deferredResult = GlobalScope.async {
            delay(5000) // Simulate some background work
            "Coroutine 1 completed"
        }

        // Do some other work in the meantime

        // Retrieve the result from the deferred value

        runBlocking {
            val result = deferredResult.await()
            Log.e(TAG, "asyncBuilder: $result")
        }

        println("End")
    }

    private fun launchBuilder() {
        println("Start")

        // Launch a coroutine in a background thread
        GlobalScope.launch {
            delay(5000) // Simulate some background work
            println("Coroutine 1 completed")
        }

        // Launch another coroutine
        GlobalScope.launch {
            delay(6000) // Simulate some more background work
            println("Coroutine 2 completed")
        }

        // Let's pause the main thread for some time to allow coroutines to finish
        Thread.sleep(3000)

        println("End")
    }

    private fun lifeCycleScope() {

        lifecycleScope.launch {
            while (true) {
                delay(1000L)
                Log.e(TAG, "lifeCycleScope: Still Running")
            }
        }


    }

    @OptIn(DelicateCoroutinesApi::class)
    fun globalScopeDemo() {
        GlobalScope.launch {
            for (i in 0..50) {
                Log.e(TAG, "globalScopeDemo: Still Running.. $i")
                delay(1000L)
            }
        }
    }


    private fun increment() {
        CoroutineScope(Dispatchers.IO).launch {

            for (i in 0..10) {

                Log.e(TAG, "increment: $i")
                if (i == 5) {
                    delay(3000L)
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 10 downTo 0) {

                Log.e(TAG, "increment: $i")
                if (i == 5) {
                    delay(2000L)
                }
            }

        }
    }
}