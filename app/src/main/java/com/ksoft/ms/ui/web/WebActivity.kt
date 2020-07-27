package com.ksoft.ms.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.ksoft.ms.R
import com.ksoft.ms.databinding.ActivityWebBinding
import com.ksoft.ms.ui.base.BaseActivity

class WebActivity : BaseActivity<WebViewModel, ActivityWebBinding>() {

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"

        fun createIntent(context: Context, url: String) = Intent(context, WebActivity::class.java)
            .putExtra(EXTRA_URL, url)
    }

    override val layoutRes = R.layout.activity_web
    override val viewModelClass = WebViewModel::class

    private val url: String by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringExtra(EXTRA_URL) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.url = url
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
    }
}