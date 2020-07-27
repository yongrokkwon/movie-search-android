package com.ksoft.ms.binding

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String?) {
    view.loadUrl(url)
}

@BindingAdapter("enableJavaScript")
fun enableJavaScript(view: WebView, flag: Boolean) {
    view.settings?.javaScriptEnabled = flag
}
