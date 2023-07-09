package com.vurda.fina.util

import com.onesignal.OneSignal
import com.vurda.fina.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "433bb604-3001-4967-8d82-0e4612794182"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}