package com.hellhot.competition.ads

import android.content.Context
import com.badlogic.gdx.utils.Disposable
import com.hellhot.competition.MainActivity
import com.hellhot.competition.log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AdAppOpen: Disposable {

    // test: ca-app-pub-3940256099942544/9257395921
    // original: ca-app-pub-6757786625316217/4363949470
    private val AD_UID = "ca-app-pub-6757786625316217/4363949470"
    
    private val coroutineMain = CoroutineScope(Dispatchers.Main)

    private var adAppOpen: AppOpenAd? = null
    private val adRequest = AdRequest.Builder().build()

    val isLoaded: Boolean get() = adAppOpen != null

    fun loadAd(context: Context) {
        if (adAppOpen != null) return

        coroutineMain.launch {
            AppOpenAd.load(context, AD_UID, adRequest, object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    log("AdAppOpen onAdLoaded")
                    adAppOpen = ad
                    adAppOpen?.fullScreenContentCallback = getFullScreenContentCallback(context)
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    adAppOpen = null
                    log("AdAppOpen onAdFailedToLoad: ${loadAdError.message}")
                }
            })
        }
    }

    fun showAd(activity: MainActivity) {
        coroutineMain.launch { adAppOpen?.show(activity) }
    }

    override fun dispose() {
        adAppOpen = null
        coroutineMain.cancel()
    }

    private fun getFullScreenContentCallback(context: Context) = object: FullScreenContentCallback() {
        override fun onAdClicked() {
            log("AdAppOpen onAdClicked")
        }

        override fun onAdDismissedFullScreenContent() {
            log("AdAppOpen onAdDismissedFullScreenContent")
            adAppOpen = null
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            log("AdAppOpen onAdFailedToShowFullScreenContent")

            adAppOpen = null
            loadAd(context)
        }

        override fun onAdImpression() {
            log("AdAppOpen onAdImpression")
        }

        override fun onAdShowedFullScreenContent() {
            log("AdAppOpen onAdShowedFullScreenContent")
        }
    }

}