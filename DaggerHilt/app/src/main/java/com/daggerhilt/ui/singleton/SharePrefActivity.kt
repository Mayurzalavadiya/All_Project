package com.daggerhilt.ui.singleton

import android.view.View
import com.daggerhilt.databinding.ActivitySharePrefBinding
import com.daggerhilt.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SharePrefActivity : BaseActivity() {

    private lateinit var binding: ActivitySharePrefBinding

    @Inject
    lateinit var toastGenerate: ToastGenerate

    override fun createView(): View {
        binding = ActivitySharePrefBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        setClickListener()
    }


    private fun setClickListener() = with(binding) {
        buttonSave.setOnClickListener {
            if (textViewEmail.text.toString().trim().isNotBlank()) {
                myPref.setStringPrefs("Email", editTextEmail.text.toString().trim())
                editTextEmail.setText("")
                toastGenerate.toast(this@SharePrefActivity,textViewEmail.text.toString())
            }
        }

        buttonNext.setOnClickListener {
            moveToNextScreen(SecondActivity::class.java)
        }
    }

}