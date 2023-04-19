package com.hsr.bkm.mobile.util

import com.onesignal.OneSignal
import com.hsr.bkm.mobile.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "52797dc9-a4fb-41b6-8ef0-a3074a185d4f"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}