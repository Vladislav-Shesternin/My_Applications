package com.veldan.boost.and.clean.simpleapp

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.MobileAds

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        MobileAds.initialize(this)
    }

}
