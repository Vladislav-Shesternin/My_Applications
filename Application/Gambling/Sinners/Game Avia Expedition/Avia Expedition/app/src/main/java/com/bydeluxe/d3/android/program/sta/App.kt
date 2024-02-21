package com.bydeluxe.d3.android.program.sta

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appContext: Context private set

private const val ONESIGNAL_APP_ID = "2d29e29d-a04d-40bd-953b-8854601a8e5a"

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