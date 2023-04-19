package com.play.jkr.ggame

import android.app.Application
import android.content.Context
import com.play.jkr.ggame.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        OneSignalUtil.initialize()
    }

}
