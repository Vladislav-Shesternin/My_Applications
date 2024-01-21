package com.favsport.slots

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AdInterstitial {

    // test: ca-app-pub-3940256099942544/1033173712
    // original: ca-app-pub-6757786625316217/2296534812

    private val AD_UID = "ca-app-pub-6757786625316217/2296534812"

    private var mInterstitialAd: InterstitialAd? = null
    private var adRequest = AdRequest.Builder().build()

    private val coroutineMain = CoroutineScope(Dispatchers.Main)

    fun load() {
        coroutineMain.launch {
            InterstitialAd.load(activityContext, AD_UID, adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    load()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
        }
    }

    fun show() {
        coroutineMain.launch {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(activityContext)
            } else {
                load()
            }
        }
    }

    fun dispose() {
        coroutineMain.cancel()
    }

}