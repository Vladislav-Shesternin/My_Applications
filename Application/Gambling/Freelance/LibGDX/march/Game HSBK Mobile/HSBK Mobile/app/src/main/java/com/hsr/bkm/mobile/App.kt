package com.hsr.bkm.mobile

import android.app.Application
import android.content.Context
import com.hsr.bkm.mobile.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
