package com.gusarove.digitalexchange

import android.app.Application
import android.content.Context
import com.gusarove.digitalexchange.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
