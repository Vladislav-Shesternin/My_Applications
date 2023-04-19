package com.gs.vest.ask.util

import com.onesignal.OneSignal
import com.gs.vest.ask.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "8d825f4e-7885-47b2-8a6a-ba304765ff36"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}