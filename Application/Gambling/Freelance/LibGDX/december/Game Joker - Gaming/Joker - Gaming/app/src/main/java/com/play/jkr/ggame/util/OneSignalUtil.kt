package com.play.jkr.ggame.util

import com.play.jkr.ggame.appContext
import com.onesignal.OneSignal

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "12e9d08d-701e-4e61-8b76-0733a1b210ac"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}