package com.finan.cial.quizz

import android.app.Application
import android.content.Context
import com.finan.cial.quizz.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
