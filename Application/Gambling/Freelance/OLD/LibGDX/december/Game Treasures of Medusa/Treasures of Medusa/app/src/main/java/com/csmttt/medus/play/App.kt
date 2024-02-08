package com.csmttt.medus.play

import android.app.Application
import android.content.Context
import com.csmttt.medus.play.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        OneSignalUtil.initialize()
    }

}
