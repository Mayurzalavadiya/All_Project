package com.social.app.ui.auth.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.social.app.R
import com.social.app.databinding.AuthDialogBinding
import com.social.app.ui.auth.interfaces.AuthenticationListener

class AuthenticationDialog(context: Context, val listener: AuthenticationListener) :
    Dialog(context) {

    private lateinit var binding: AuthDialogBinding

    private var request_url: String? = null
    private var redirect_url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        redirect_url = context.resources.getString(R.string.redirect_url)

        request_url = context.resources.getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                context.resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url + "&response_type=token&display=touch&scope=public_content"

        initializeWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {
        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        request_url?.let { webView.loadUrl(it) }
        webView.webViewClient = webViewClient
    }

    var webViewClient = object : WebViewClient() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (redirect_url?.let { url.startsWith(it) } == true) {
                dismiss()
                return true
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (url.contains("access_token=")) {
                val uri = Uri.parse(url)
                var access_token = uri.encodedFragment
                access_token = access_token!!.substring(access_token.lastIndexOf("=") + 1)
                Log.e("access_token", access_token)
                listener.onTokenReceived(access_token)
                dismiss()
            } else if (url.contains("?error")) {
                Log.e("access_token", "getting error fetching access token")
                dismiss()
            }
        }
    }
}