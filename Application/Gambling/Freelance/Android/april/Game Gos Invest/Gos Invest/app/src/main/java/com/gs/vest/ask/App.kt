package com.gs.vest.ask

import android.app.Application
import android.content.Context
import com.gs.vest.ask.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
