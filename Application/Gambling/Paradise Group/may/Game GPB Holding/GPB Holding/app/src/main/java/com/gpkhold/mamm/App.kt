package com.gpkhold.mamm

import android.app.Application
import android.content.Context
import com.gpkhold.mamm.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
