package com.example.androidbasics.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.androidbasics.R

class Activity1 : AppCompatActivity() {

    private var buttonNext: AppCompatButton? = null
    private var imageView: AppCompatImageView? = null
    private var textView: AppCompatTextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val intArray = intArrayOf(1, 2, 3, 4, 5, 6)
        val stringArray = arrayOf("absb", "sjhjhsj", "sjhj", "sjjh")
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val booleanArray = booleanArrayOf(true, false, true, false)

        buttonNext = findViewById(R.id.buttonNext)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)

        buttonNext?.setOnClickListener {
            val i = Intent(this, Activity2::class.java)
            i.putExtra("Int", 10)
            i.putExtra("String", "Mayur")
            i.putExtra("Char", 'A')
            i.putExtra("Float", 2f)
            i.putExtra("Double", 2.0)
            i.putExtra("Boolean", true)
            i.putExtra("Long", 1111111111111)
            i.putExtra("StringArray", stringArray)
            i.putExtra("IntArray", intArray)
            i.putExtra("FloatArray", floatArray)
            i.putExtra("BooleanArray", booleanArray)
//            val dummyObject = DummyObject("Mujeeb")
//            i.putExtra("object", dummyObject )
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }

        onSharedIntent()
    }


    private fun onSharedIntent() {
        val receiveIntent = intent
        val receivedAction = receiveIntent.action
        val receivedType = receiveIntent.type
        if (receivedAction == Intent.ACTION_SEND) {

            // check mime type
            if (receivedType!!.startsWith("text/")) {
                val receivedText = receiveIntent
                    .getStringExtra(Intent.EXTRA_TEXT)
                if (receivedText != null) {
                    textView?.visibility = View.GONE
                    Toast.makeText(this, receivedText, Toast.LENGTH_SHORT).show()
                }
            } else if (receivedType.startsWith("image/")) {
                val receivedImage = receiveIntent
                    .getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
                if (receivedImage != null) {
                    imageView?.visibility = View.VISIBLE
                    imageView?.setImageURI(receivedImage)
                }
            }
        }
    }
}