package com.vbtb.game

import android.app.Application
import android.content.Context
import com.vbtb.game.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
