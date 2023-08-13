package com.veldan.lbjt.util.admob

import android.view.View
import com.badlogic.gdx.utils.Disposable
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.veldan.lbjt.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BannerUtil(
    private val banner   : AdView,
    private val coroutine: CoroutineScope
) {

    private val adRequest by lazy { AdRequest.Builder().build() }


    init {
        /*banner.adListener = object: AdListener() {
            override fun onAdClicked() { log("onAdClicked") }
            override fun onAdClosed() { log("onAdClosed") }
            override fun onAdFailedToLoad(adError : LoadAdError) { log("onAdFailedToLoad: $adError") }
            override fun onAdImpression() { log("onAdImpression") }
            override fun onAdLoaded() { log("onAdLoaded") }
            override fun onAdOpened() { log("onAdOpened") }
        }*/
    }

    fun load() {
         coroutine.launch(Dispatchers.Main) { banner.loadAd(adRequest) }
    }

    fun show() {
        coroutine.launch(Dispatchers.Main) { banner.visibility = View.VISIBLE }
    }

    fun hide() {
        coroutine.launch(Dispatchers.Main) { banner.visibility = View.INVISIBLE }
    }

}