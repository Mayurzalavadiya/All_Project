package com.example.androidbasics.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.androidbasics.R

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var webview: WebView = findViewById(R.id.web)

        webview.loadUrl("https://www.geeksforgeeks.org/how-to-use-webview-in-android/")

        webview.settings.javaScriptEnabled = true

        webview.webViewClient = WebViewClient()
    }
}