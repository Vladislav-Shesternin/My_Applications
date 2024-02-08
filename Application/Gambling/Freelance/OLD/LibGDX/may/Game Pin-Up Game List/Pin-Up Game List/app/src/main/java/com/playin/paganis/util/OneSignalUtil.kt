package com.playin.paganis.util

import com.onesignal.OneSignal
import com.playin.paganis.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "a58e6b1d-59ee-4956-90fd-21338479b56a"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}