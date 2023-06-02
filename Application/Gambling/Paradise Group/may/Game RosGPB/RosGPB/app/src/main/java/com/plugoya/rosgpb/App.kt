package com.plugoya.rosgpb

import android.app.Application
import android.content.Context
import com.plugoya.rosgpb.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
