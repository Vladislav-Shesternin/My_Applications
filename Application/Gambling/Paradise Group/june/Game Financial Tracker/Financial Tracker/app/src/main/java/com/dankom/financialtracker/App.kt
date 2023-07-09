package com.dankom.financialtracker

import android.app.Application
import android.content.Context
import com.dankom.financialtracker.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
