package com.mariam.cleverfinancier

import android.app.Application
import android.content.Context
import com.mariam.cleverfinancier.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
