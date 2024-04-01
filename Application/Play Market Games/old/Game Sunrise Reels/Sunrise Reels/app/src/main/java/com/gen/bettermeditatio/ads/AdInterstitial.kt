package com.gen.bettermeditatio.ads

import android.content.Context
import com.badlogic.gdx.utils.Disposable
import com.gen.bettermeditatio.MainActivity
import com.gen.bettermeditatio.log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AdInterstitial: Disposable {

    // test: ca-app-pub-3940256099942544/1033173712
    // original: ca-app-pub-6757786625316217/8036203819

    private val AD_UID = "ca-app-pub-6757786625316217/8036203819"

    private val coroutineMain = CoroutineScope(Dispatchers.Main)

    private var adInterstitial: InterstitialAd? = null
    private var adRequest = AdRequest.Builder().build()

    fun loadAd(context: Context) {
        if (adInterstitial != null) return

        coroutineMain.launch {
            InterstitialAd.load(context, AD_UID, adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    log("AdInterstitial onAdLoaded")
                    adInterstitial = interstitialAd
                    adInterstitial?.fullScreenContentCallback = getFullScreenContentCallback(context)
                }
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adInterstitial = null
                    log("AdInterstitial onAdFailedToLoad: ${adError.message}")
                }
            })
        }
    }

    fun showAd(activity: MainActivity) {
        coroutineMain.launch { adInterstitial?.show(activity) ?: loadAd(activity) }
    }

    override fun dispose() {
        adInterstitial = null
        coroutineMain.cancel()
    }

    private fun getFullScreenContentCallback(context: Context) = object: FullScreenContentCallback() {
        override fun onAdClicked() {
            log("AdInterstitial onAdClicked")
        }

        override fun onAdDismissedFullScreenContent() {
            log("AdInterstitial onAdDismissedFullScreenContent")

            adInterstitial = null
            loadAd(context)
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            log("AdInterstitial onAdFailedToShowFullScreenContent")

            adInterstitial = null
            loadAd(context)
        }

        override fun onAdImpression() {
            log("AdInterstitial onAdImpression")
        }

        override fun onAdShowedFullScreenContent() {
            log("AdInterstitial onAdShowedFullScreenContent")
        }
    }

}