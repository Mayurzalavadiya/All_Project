package com.example.androidbasics.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.widget.AppCompatTextView
import com.example.androidbasics.R

class Activity3 : AppCompatActivity() {
    private var textView: AppCompatTextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        textView = findViewById(R.id.textView)

       textView?.setOnClickListener{
           val intent = Intent(this,Activity4::class.java)
           startActivity(intent)
       }

//        when {
//            intent?.action == Intent.ACTION_SEND -> {
//                if ("text/plain" == intent.type) {
//                    handleSendText(intent) // Handle text being sent
//                } else if (intent.type?.startsWith("image/") == true) {
//                    handleSendImage(intent) // Handle single image being sent
//                }
//            }
//
//            intent?.action == Intent.ACTION_SEND_MULTIPLE
//                    && intent.type?.startsWith("image/") == true -> {
//                handleSendMultipleImages(intent) // Handle multiple images being sent
//            }
//
//            else -> {
//                // Handle other intents, such as being started from the home screen
//            }
//        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            // Update UI to reflect text being shared
        }
    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect image being shared
        }
    }

    private fun handleSendMultipleImages(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            // Update UI to reflect multiple images being shared
        }
    }
}