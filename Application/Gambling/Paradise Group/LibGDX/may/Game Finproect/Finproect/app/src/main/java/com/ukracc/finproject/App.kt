package com.ukracc.finproject

import android.app.Application
import android.content.Context
import com.ukracc.finproject.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
