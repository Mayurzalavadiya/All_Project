package com.example.androidbasics.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.androidbasics.R


class Activity2 : AppCompatActivity() {

    private val TAG: String = Activity2::class.java.simpleName

    private var textView: AppCompatTextView? = null

    private var buttonNext: AppCompatButton? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)


        textView = findViewById(R.id.textView)

        val string = intent.getStringExtra("String")
        val int = intent.getIntExtra("Int", 0)
        val char = intent.getCharExtra("Char", 'D')
        val float = intent.getFloatExtra("Float", 0f)
        val double = intent.getDoubleExtra("Double", 0.0)
        val boolean = intent.getBooleanExtra("Boolean", false)
        val long = intent.getLongExtra("Long", 11111111)
        val intArray = intent.getIntArrayExtra("IntArray")
        val stringArray = intent.getStringArrayExtra("StringArray")
        val floatArray = intent.getFloatArrayExtra("FloatArray")
        val booleanArray = intent.getFloatArrayExtra("BooleanArray")

        stringArray?.forEach { it -> Log.i(TAG, "String: $it") }

        textView?.text =
            "$string\n$int\n$char\n$float\n$double\n$boolean\n$long"


        buttonNext = findViewById(R.id.buttonNext)
        buttonNext?.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)

//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        val receivedText = intent
            .getStringExtra(Intent.EXTRA_TEXT)
        if (receivedText != null) {
            Toast.makeText(this, receivedText, Toast.LENGTH_SHORT).show()
        }
    }
}