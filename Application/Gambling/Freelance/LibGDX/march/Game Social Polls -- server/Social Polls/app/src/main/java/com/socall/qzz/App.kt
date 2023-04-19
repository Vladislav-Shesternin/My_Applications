package com.socall.qzz

import android.app.Application
import android.content.Context
import com.socall.qzz.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
