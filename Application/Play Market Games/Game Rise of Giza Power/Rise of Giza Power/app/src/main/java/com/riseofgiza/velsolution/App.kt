package com.riseofgiza.velsolution

import android.app.Application
import android.content.Context
import com.riseofgiza.velsolution.util.AdAppOpen
import com.riseofgiza.velsolution.util.log
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
