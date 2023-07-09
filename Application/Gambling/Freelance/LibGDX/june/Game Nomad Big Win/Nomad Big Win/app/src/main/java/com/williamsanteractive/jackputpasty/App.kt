package com.williamsanteractive.jackputpasty

import android.app.Application
import android.content.Context
import com.williamsanteractive.jackputpasty.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
