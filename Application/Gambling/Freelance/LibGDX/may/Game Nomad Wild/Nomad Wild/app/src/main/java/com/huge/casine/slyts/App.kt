package com.huge.casine.slyts

import android.app.Application
import android.content.Context
import com.huge.casine.slyts.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
