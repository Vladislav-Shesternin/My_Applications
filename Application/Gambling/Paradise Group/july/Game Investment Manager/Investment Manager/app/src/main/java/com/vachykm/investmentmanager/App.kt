package com.vachykm.investmentmanager

import android.app.Application
import android.content.Context

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
       // OneSignalUtil.initialize()
    }

}
