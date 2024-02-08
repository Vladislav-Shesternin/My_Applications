package com.violette.quiz.util

import com.violette.quiz.appContext
import com.onesignal.OneSignal

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "37412ec4-e201-41c5-9567-bdfdbd53859f"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}