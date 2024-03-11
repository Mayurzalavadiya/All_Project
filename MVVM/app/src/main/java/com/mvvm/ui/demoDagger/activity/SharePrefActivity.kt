package com.mvvm.ui.demoDagger.activity

import android.util.Log
import android.view.View
import com.mvvm.databinding.ActivitySharePrefBinding
import com.mvvm.di.App
import com.mvvm.ui.base.BaseActivity
import javax.inject.Inject
import javax.inject.Named

class SharePrefActivity : BaseActivity() {

    private lateinit var binding: ActivitySharePrefBinding


    @Inject
    lateinit var firstString: String

    @Inject
    @Named("Two")
    lateinit var secondString: String


    override fun createView(): View {
        binding = ActivitySharePrefBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
//        showShackBarMessage(findViewById(android.R.id.content),"Hello, Welcome")
        val application = application
        if (application is App) {
            application.appComponent.inject(this)
        }
        Log.e("TAG", "onBindActivity: $firstString :: $secondString")
        setClickListener()
    }


    private fun setClickListener() = with(binding) {
        buttonSave.setOnClickListener {
            if (textViewEmail.text.toString().trim().isNotBlank()) {
                myPref.setStringPrefs("Email", editTextEmail.text.toString().trim())
                editTextEmail.setText("")
            }
        }

        buttonNext.setOnClickListener {
            moveToNextScreen(SecondActivity::class.java)
        }
    }

}