package com.hk.stck.nord

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.hk.stck.nord.util.OneSignalUtil

lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        OneSignalUtil.initialize()
    }

}
