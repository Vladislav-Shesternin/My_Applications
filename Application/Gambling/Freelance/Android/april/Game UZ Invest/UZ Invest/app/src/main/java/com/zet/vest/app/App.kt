package com.zet.vest.app

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.zet.vest.app.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
