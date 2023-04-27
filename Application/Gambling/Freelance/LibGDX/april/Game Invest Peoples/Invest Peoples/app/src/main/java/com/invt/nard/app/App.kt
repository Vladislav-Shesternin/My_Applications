package com.invt.nard.app

import android.app.Application
import android.content.Context
import com.invt.nard.app.util.OneSignalUtil

lateinit var appContext: Context private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}