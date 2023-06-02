package com.karpenkov.budgetgid.util

import com.onesignal.OneSignal
import com.karpenkov.budgetgid.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "1707885a-da05-4622-a2c5-470cde873505"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}