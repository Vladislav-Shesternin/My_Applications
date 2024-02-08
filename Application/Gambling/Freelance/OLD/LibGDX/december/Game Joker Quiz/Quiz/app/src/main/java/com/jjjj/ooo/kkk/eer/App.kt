package com.jjjj.ooo.kkk.eer

import android.app.Application
import android.content.Context
import com.jjjj.ooo.kkk.eer.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        OneSignalUtil.initialize()
    }

}
