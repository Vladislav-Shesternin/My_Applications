package com.playin.paganis

import android.app.Application
import android.content.Context
import com.playin.paganis.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
