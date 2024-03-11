package com.example.listviewdemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listviewdemo.databinding.ActivityTaskBinding
import com.example.listviewdemo.demo.flexbox.activity.FlexBoxListActivity
import com.example.listviewdemo.demo.list.activity.RecyclerActivity
import com.example.listviewdemo.demo.staggered.activity.StaggeredListActivity
import com.example.listviewdemo.task1.activity.ListActivity
import com.example.listviewdemo.task2.activity.StaggeredActivity
import com.example.listviewdemo.task3.activity.GridActivity
import com.example.listviewdemo.task4.activity.ProductActivity
import com.example.listviewdemo.task5.activity.MultiSelectActivity
import com.example.listviewdemo.task6.activity.SingleSelectActivity
import com.example.listviewdemo.task7.activity.DemoActivity
import com.example.listviewdemo.task8.activity.Task8Activity

class AllTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        buttonListDemo.setOnClickListener {
            moveToNextScreen(RecyclerActivity::class.java)
        }

        buttonStaggeredDemo.setOnClickListener {
            moveToNextScreen(StaggeredListActivity::class.java)
        }
        buttonFlexDemo.setOnClickListener {
            moveToNextScreen(FlexBoxListActivity::class.java)
        }

        buttonGridListView.setOnClickListener {
            moveToNextScreen(GridActivity::class.java)
        }


        buttonStaggeredListView.setOnClickListener {
            moveToNextScreen(StaggeredActivity::class.java)
        }

        buttonLinearListView.setOnClickListener {
            moveToNextScreen(ListActivity::class.java)
        }
        buttonCart.setOnClickListener {
            moveToNextScreen(ProductActivity::class.java)
        }
        buttonMultiSelect.setOnClickListener {
            moveToNextScreen(MultiSelectActivity::class.java)
        }
        buttonSingleSelect.setOnClickListener {
            moveToNextScreen(SingleSelectActivity::class.java)
        }
        buttonTask7.setOnClickListener {
            moveToNextScreen(DemoActivity::class.java)
        }
        buttonTask8.setOnClickListener {
            moveToNextScreen(Task8Activity::class.java)
        }



    }

    private fun moveToNextScreen(activity: Class<*>) {

        startActivity(Intent(this, activity))
    }
}