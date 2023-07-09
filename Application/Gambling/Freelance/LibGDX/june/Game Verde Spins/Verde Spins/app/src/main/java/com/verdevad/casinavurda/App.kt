package com.verdevad.casinavurda

import android.app.Application
import android.content.Context
import com.verdevad.casinavurda.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
