package com.aztec.blaze

import android.app.Application
import android.content.Context
import com.aztec.blaze.util.AdAppOpen
import com.aztec.blaze.util.log
import com.google.android.gms.ads.MobileAds

lateinit var appContext: Context

var adAppOpen: AdAppOpen? = null
    private set

class App: Application() {


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        MobileAds.initialize(this) {
            log("Init AdMob")
            adAppOpen = AdAppOpen().apply { loadAd(appContext) }
        }


    }

}
