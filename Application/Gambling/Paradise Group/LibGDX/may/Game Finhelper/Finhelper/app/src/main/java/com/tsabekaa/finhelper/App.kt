package com.tsabekaa.finhelper

import android.app.Application
import android.content.Context
import com.tsabekaa.finhelper.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
