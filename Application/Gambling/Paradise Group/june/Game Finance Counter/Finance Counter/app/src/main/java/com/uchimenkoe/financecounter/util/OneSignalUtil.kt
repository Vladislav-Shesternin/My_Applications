package com.uchimenkoe.financecounter.util

import com.onesignal.OneSignal
import com.uchimenkoe.financecounter.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "e373517d-d6ae-4a8e-83e8-b6553ed172ab"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}