package com.avietor.onlaneslets.util

import com.onesignal.OneSignal
import com.avietor.onlaneslets.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "1dcba954-3015-479f-9eef-ea0f0e93543f"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}