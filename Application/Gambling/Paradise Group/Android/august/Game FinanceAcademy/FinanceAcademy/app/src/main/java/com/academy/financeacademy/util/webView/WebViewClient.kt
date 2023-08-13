package com.academy.financeacademy.util.webView

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.R

var isClearHistory = true

class WebViewClient(private val activity: MainActivity): WebViewClient() {

    private lateinit var intent: Intent

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        when {
            url.startsWith("mailto") -> {
                intent = Intent(Intent.ACTION_SEND)
                intent.type = "plain/text"
                intent.putExtra(Intent.EXTRA_EMAIL, url.replace("mailto:", ""))
                activity.startActivity(Intent.createChooser(intent, "Send email"))
            }
            url.startsWith("tel:") -> {
                intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse(url)
                activity.startActivity(intent)
            }
            url.startsWith("https://diia.app") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.data?.authority.toString()
                view?.context?.startActivity(intent)
            }
            url.startsWith("https://t.me/joinchat") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view?.context?.startActivity(intent)
            }

            url.startsWith("tg:") -> {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view?.context?.startActivity(intent)
            }

            Uri.parse(url).host == "localhost" -> {
                MainActivity.startFragmentIdFlow.tryEmit(R.id.gameFragment)
            }

            else -> if (url.startsWith("http://") || url.startsWith("https://")) return false
        }
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        activity.lottie.hideLoader()
        if (isClearHistory) {
            isClearHistory = false
            view?.clearHistory()
        }
    }

}