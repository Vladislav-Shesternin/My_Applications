package com.vurda.start.util

import com.onesignal.OneSignal
import com.vurda.start.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "bf86b00a-9e8e-455e-adff-03cdadc47bbd"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}