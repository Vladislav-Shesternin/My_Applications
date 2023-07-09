package com.bango.weld.androit

import android.app.Application
import android.content.Context
import com.bango.weld.androit.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
