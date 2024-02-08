package com.aztec.firevoll

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.aztec.firevoll.ads.AdAppOpen

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
