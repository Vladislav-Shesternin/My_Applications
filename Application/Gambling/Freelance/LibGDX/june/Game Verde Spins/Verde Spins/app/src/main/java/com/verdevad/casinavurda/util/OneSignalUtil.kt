package com.verdevad.casinavurda.util

import com.onesignal.OneSignal
import com.verdevad.casinavurda.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "5e1b962f-fd4f-4881-bc9c-5041c04abf98"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}