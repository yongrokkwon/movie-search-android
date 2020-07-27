package com.ksoft.ms.ui.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.ksoft.ms.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : Activity() {

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"

        fun createIntent(context: Context, url: String) = Intent(context, WebActivity::class.java)
            .putExtra(EXTRA_URL, url)
    }

    private val url: String by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringExtra(EXTRA_URL) ?: ""
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

}