package com.uchimenkoe.financecounter

import android.app.Application
import android.content.Context
import com.uchimenkoe.financecounter.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
