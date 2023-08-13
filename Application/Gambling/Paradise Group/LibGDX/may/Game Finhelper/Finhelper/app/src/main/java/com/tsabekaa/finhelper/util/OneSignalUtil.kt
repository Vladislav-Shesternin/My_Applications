package com.tsabekaa.finhelper.util

import com.onesignal.OneSignal
import com.tsabekaa.finhelper.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "883feb36-b5e2-4345-b2ae-40204396e8da"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}