package com.invt.nard.app.util

import com.onesignal.OneSignal
import com.invt.nard.app.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "db4f3338-691f-4b9b-9318-701bd23192e1"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}