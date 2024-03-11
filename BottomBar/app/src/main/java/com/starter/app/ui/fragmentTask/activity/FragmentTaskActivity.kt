package com.starter.app.ui.fragmentTask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.starter.app.R
import com.starter.app.databinding.ActivityFragmentTaskBinding
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.fragmentTask.fragment.AFragment
import com.starter.app.ui.home.fragment.MainFragment

class FragmentTaskActivity : BaseActivity() {
    private lateinit var binding: ActivityFragmentTaskBinding

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        binding = ActivityFragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(AFragment::class.java).replace(false)
    }
}