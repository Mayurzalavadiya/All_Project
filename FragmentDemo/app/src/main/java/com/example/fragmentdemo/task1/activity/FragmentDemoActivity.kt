package com.example.fragmentdemo.task1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentdemo.task1.fragment.FirstFragment
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityFragmentDemoBinding

class FragmentDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = FirstFragment()
        firstFragment.arguments = Bundle().apply {
            putString("String", "This is First Fragment")
            putString("Text", "Next >")
            putInt("Int", 1)
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, firstFragment, FirstFragment::class.java.simpleName)
            .commit()

//        Handler(Looper.getMainLooper()).postDelayed({
//            Log.i("TAG", "onCreate: ${supportFragmentManager.fragments.size} ")
//
//        },5000)

//
//        val firstFragment1 = FirstFragment()
//        firstFragment1.arguments = Bundle().apply {
//            putString("String", "This is second Fragment")
//        }
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentContainer1, firstFragment1)
//            .commit()
    }
}