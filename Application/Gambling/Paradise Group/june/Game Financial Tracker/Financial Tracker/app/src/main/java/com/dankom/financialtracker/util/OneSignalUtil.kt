package com.dankom.financialtracker.util

import com.onesignal.OneSignal
import com.dankom.financialtracker.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "f9e80435-7321-41bb-99b1-902bcccbb347"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}