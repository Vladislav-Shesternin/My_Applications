package com.karpenkov.budgetgid

import android.app.Application
import android.content.Context
import com.karpenkov.budgetgid.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
