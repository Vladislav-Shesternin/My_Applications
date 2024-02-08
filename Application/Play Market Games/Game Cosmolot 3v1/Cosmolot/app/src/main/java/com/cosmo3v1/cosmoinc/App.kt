package com.cosmo3v1.cosmoinc

import android.app.Application
import android.content.Context
import com.cosmo3v1.cosmoinc.util.AdAppOpen
import com.cosmo3v1.cosmoinc.util.log
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

lateinit var appContext: Context

var adAppOpen: AdAppOpen? = null

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
