package com.starter.app.ui.testTask.activity

import android.os.Bundle
import android.view.View
import com.starter.app.R
import com.starter.app.databinding.ActivityTaskBinding
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.testTask.fragment.MyQuotesFragment

class TaskActivity : BaseActivity() {

    private lateinit var activityTaskBinding: ActivityTaskBinding

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        activityTaskBinding = ActivityTaskBinding.inflate(layoutInflater)
        return activityTaskBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(MyQuotesFragment::class.java).replace(false)
    }
}