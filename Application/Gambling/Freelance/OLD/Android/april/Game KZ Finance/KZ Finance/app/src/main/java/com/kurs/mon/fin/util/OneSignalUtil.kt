package com.kurs.mon.fin.util

import com.onesignal.OneSignal
import com.kurs.mon.fin.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "5875ed40-8c91-40e0-9684-0291ef7fc7e0"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}