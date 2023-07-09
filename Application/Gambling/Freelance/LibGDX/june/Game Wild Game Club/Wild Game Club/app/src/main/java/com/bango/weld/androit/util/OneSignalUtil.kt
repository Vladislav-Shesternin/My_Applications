package com.bango.weld.androit.util

import com.onesignal.OneSignal
import com.bango.weld.androit.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "23b4a677-b41d-4095-ab11-4cc5fba4dbc8"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}