package com.jettylucketjet1wincasino.onewinslots1win

import android.app.Application
import android.content.Context
import com.jettylucketjet1wincasino.onewinslots1win.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
