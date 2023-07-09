package com.golovkoe.cryptosafe.util

import com.onesignal.OneSignal
import com.golovkoe.cryptosafe.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "27e4d1d7-d54c-4e67-9dcd-a52448f07b8e"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}