package com.vbt.game.sptr

import android.app.Application
import android.content.Context

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}
