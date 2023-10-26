package com.bettilt.mobile.pt.util.sudaNeXodi_TudaToje

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bettilt.mobile.pt.Fragolino
import com.bettilt.mobile.pt.R

var isClearHistory  = true
var isCheckTelegaph = true

class Drocod(val activity: Fragolino): WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        if (isCheckTelegaph && (url?.startsWith("https://telegra.ph/") == true)) {
            Fragolino.startIdFlow.tryEmit(R.id.libGDXFragment)
        }

        activity.lottie.hideLoader()

        if (isClearHistory) {
            isClearHistory = false
            view?.clearHistory()
        }
    }

}