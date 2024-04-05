package com.example.coroutines.threads

import android.os.Bundle
import android.util.Log
import com.example.coroutines.activity.BaseActivity
import com.example.coroutines.databinding.ActivityThreadBinding
import kotlin.concurrent.thread

class ThreadActivity : BaseActivity() {

    private lateinit var binding: ActivityThreadBinding

    private var TAG = ThreadActivity::class.java.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonThread.setOnClickListener {
//            interrupted()
            threadGroup()
//            getState()

        }
    }

    private fun threads() {

        thread(
            name = "first",
            priority = 1
        ) {
            Log.e(TAG, "threads: ${Thread.currentThread().id} ${Thread.currentThread().name}")
            for (i in 0..10) {

//                if (i == 5) {
//                    Thread.sleep(8000)
//                }
                Thread.sleep(1000)
                Log.e(TAG, "setClickListener: $i")

            }

        }

        thread(
            name = "second",
            priority = 5
        )
        {
            Log.e(TAG, "threads: ${Thread.currentThread().id} ${Thread.currentThread().name}")
            for (i in 10 downTo 0) {
                Thread.sleep(1000)
                Log.e(TAG, "setClickListener: $i")

            }
        }
    }

    private fun demo1() {

        Thread {
            Thread.sleep(5000)
            Log.e(TAG, "demo1: ")
        }.start()

        val thread = object : Thread() {
            override fun run() {
                sleep(3000)
                Log.e(TAG, "run: ")
            }
        }

        thread.start()
        thread.interrupt()
        Log.e(TAG, "demo1: ${readBooleanPrefsVal(IS_LOGIN)}")

        Thread.sleep(5000)
    }


    private fun runnable() {

        val runnable = Runnable {
            for (i in 0..10) {
                Log.e(TAG, "increment: $i")
            }
        }

        val thread = Thread(runnable)
        thread.name = "Demo Thread"
        thread.start()
        Log.e(
            TAG,
            "runnable: ${thread.name} ${thread.isAlive} ${Thread.currentThread().interrupt()}"
        )
    }


    private fun setTextview() = with(binding) {
        Thread(Runnable {
            while (true) {
                runOnUiThread { textview.text = "Test 1" }

                Thread.sleep(1000)
                runOnUiThread { textview.text = "Test 2" }

                Thread.sleep(1000)
            }
        }).start()
    }

    private fun creating10kThread() {
        for (i in 1..100000) {
            thread {
                Log.e(TAG, "creating10kThread: $i")
            }
        }
    }

    private fun demo2() {
        thread {
            increment()
            Thread.sleep(5000)
            decrement()
        }
    }

    private fun increment() {
        for (i in 0..10) {
            Log.e(TAG, "increment: $i")
        }
    }

    private fun decrement() {
        for (i in 10 downTo 1) {
            Log.e(TAG, "increment: $i")
        }
    }

    private fun interrupted() {
        val thread = MyClass()
        thread.start()

        // main thread calls interrupt() method on
        // child thread
        thread.interrupt()
        println("Main thread execution completes")

    }

    private fun threadGroup() {
        val thread = MyClass1()
        val threadGroup = ThreadGroup("Parent ThreadGroup")

        val thread1 = Thread(threadGroup, thread, "One")
        val thread2 = Thread(threadGroup, thread, "Two")
        val thread3 = Thread(threadGroup, thread, "Three")

        thread1.start()
        thread2.start()
        thread3.start()

        val threadGroup1 = ThreadGroup(threadGroup, "the child group")
        val threadGroup2 = ThreadGroup(threadGroup, "the child group")
        val threadGroup3 = ThreadGroup(threadGroup, "the child group")
        val threadGroup4 = ThreadGroup(threadGroup, "the child group")
        val threadGroup5 = ThreadGroup(threadGroup, "the child group")

       println("The total number of active thread groups are: " + threadGroup.activeGroupCount());
    }

    private fun getState() {
        val myClass = MyClass1()
        val thread1 = Thread(myClass)
        val thread2 = Thread(myClass)

        thread1.start()
        thread2.start()
    }

    inner class MyClass : Thread() {
        override fun run() {
            try {
                for (i in 0..4) {
                    println("Child Thread executing")

                    println(i)
                    sleep(1000)
                }
            } catch (e: InterruptedException) {
                println("InterruptedException occur")
            }
        }
    }

    inner class MyClass1 : Thread() {
        override fun run() {
            val state = currentThread().state
            println("Running thread name: " + currentThread().name)
            println("State of thread: $state")
        }
    }
}
