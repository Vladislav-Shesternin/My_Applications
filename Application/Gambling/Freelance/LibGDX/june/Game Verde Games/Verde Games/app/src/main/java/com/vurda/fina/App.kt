package com.vurda.fina

import android.app.Application
import android.content.Context
import com.vurda.fina.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
