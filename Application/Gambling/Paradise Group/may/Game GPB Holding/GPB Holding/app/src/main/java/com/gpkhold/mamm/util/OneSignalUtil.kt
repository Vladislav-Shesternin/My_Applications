package com.gpkhold.mamm.util

import com.gpkhold.mamm.appContext
import com.onesignal.OneSignal

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "b89a71dc-bb97-4ff8-bd8f-fe5752f2ac31"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}