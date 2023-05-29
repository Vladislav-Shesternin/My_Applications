package com.avietor.onlaneslets

import android.app.Application
import android.content.Context
import com.avietor.onlaneslets.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
