package com.socall.qzz.util

import com.onesignal.OneSignal
import com.socall.qzz.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "71cbc12f-091d-4211-9c58-7948db2d2799"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}