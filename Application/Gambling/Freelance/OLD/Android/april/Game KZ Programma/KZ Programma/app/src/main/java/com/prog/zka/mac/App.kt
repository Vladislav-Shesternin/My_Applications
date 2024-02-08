package com.prog.zka.mac

import android.app.Application
import android.content.Context
import com.prog.zka.mac.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
