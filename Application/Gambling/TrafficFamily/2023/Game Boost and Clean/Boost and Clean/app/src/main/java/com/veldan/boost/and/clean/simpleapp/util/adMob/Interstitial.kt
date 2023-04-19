package com.veldan.boost.and.clean.simpleapp.util.adMob

import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.veldan.boost.and.clean.simpleapp.MainActivity
import com.veldan.boost.and.clean.simpleapp.appContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Interstitial(
    val activity     : MainActivity,
    val coroutineMain: CoroutineScope,
) {

    companion object {
        private const val ID = "ca-app-pub-7255219510329022/1811765761"
    }

    private var interstitialAd: InterstitialAd? = null
    private val adRequest                 = AdRequest.Builder().build()
    private val fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdClicked() {}
        override fun onAdDismissedFullScreenContent() {
            interstitialAd = null
            load()
        }
        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            interstitialAd = null
            load()
        }
        override fun onAdImpression() {}
        override fun onAdShowedFullScreenContent() { load() }
    }



    fun load() {
        coroutineMain.launch {
            InterstitialAd.load(activity, ID, adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    this@Interstitial.interstitialAd = interstitialAd.apply {
                        this.fullScreenContentCallback = this@Interstitial.fullScreenContentCallback
                    }
                }
            })
        }
    }

    fun show(activity: MainActivity) {
        coroutineMain.launch { interstitialAd?.run { show(activity) } }
    }

}