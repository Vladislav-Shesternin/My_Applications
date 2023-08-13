package com.vitaliyi.financeanalizator.util

import com.onesignal.OneSignal
import com.vitaliyi.financeanalizator.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "cdc91f1d-e127-4448-bc1d-b0440c8587ef"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}