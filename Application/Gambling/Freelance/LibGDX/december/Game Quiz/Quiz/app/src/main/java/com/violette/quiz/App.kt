package com.violette.quiz

import android.app.Application
import android.content.Context
import com.violette.quiz.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        OneSignalUtil.initialize()
    }

}
