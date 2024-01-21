package com.hlperki.pesgllra

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appContext: Context private set

private const val ONESIGNAL_APP_ID = "5c1b61fa-cabd-4872-a52c-ab05c9f1bb3c"

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(appContext, ONESIGNAL_APP_ID)

        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }

}