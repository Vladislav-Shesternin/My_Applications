package com.ukracc.finproject.util

import com.onesignal.OneSignal
import com.ukracc.finproject.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "6683972e-8a80-41c8-b176-34a14f4c2e6c"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}