package com.vbbb.time.game

import android.app.Application
import android.content.Context
import com.vbbb.time.game.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
