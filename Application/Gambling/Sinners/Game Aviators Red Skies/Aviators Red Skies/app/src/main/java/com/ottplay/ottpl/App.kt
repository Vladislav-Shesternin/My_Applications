package com.ottplay.ottpl

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appContext: Context private set

private const val ONESIGNAL_APP_ID = "b27263f9-69ac-4437-b92b-5a35c91ad4c5"

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