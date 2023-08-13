package com.shapovalovd.financecomitet

import android.app.Application
import android.content.Context
import com.shapovalovd.financecomitet.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
