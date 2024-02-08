package com.aztec.blaze.util

import android.content.Context
import com.badlogic.gdx.utils.Disposable
import com.aztec.blaze.MainActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AdAppOpen: Disposable {

    // test: ca-app-pub-3940256099942544/9257395921
    // original: ca-app-pub-6757786625316217/1473319990
    private val AD_UID = "ca-app-pub-6757786625316217/1473319990"
    
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

}