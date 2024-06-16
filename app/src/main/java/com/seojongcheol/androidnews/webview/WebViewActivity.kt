package com.seojongcheol.androidnews.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.seojongcheol.androidnews.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")
        if (url != null) {
            setupWebView()
            binding.webView.webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    if (request?.isForMainFrame == true) {
                        showErrorView()
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideErrorView()
                }
            }
            binding.webView.loadUrl(url)
        } else {
            showErrorView()
        }
    }

    private fun setupWebView() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_DEFAULT
            domStorageEnabled = true
            textZoom = 100
            setSupportMultipleWindows(true)
            loadWithOverviewMode = true
            allowContentAccess = true
            allowFileAccess = true
            mediaPlaybackRequiresUserGesture = false
        }
    }

    private fun showErrorView() {
        binding.webView.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        binding.webView.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }
}