package com.example.mylibrary.openwebview

import android.os.Bundle
import android.webkit.WebView
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity

class WebviewActivity : BaseAppActivity() {

    private lateinit var webview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
        setContentView(R.layout.layout_activity_webview)

        title = "Webview"

        webview = findViewById(R.id.wv_basic_app)
        with(webview) {
            settings.javaScriptEnabled = true
            settings.supportZoom()
            loadUrl("https://docs.google.com/viewer?url=https://www.ufrgs.br/producao/wp-content/uploads/2019/05/Lorem-Ipsum.pdf")
        }
    }
}