package com.jjjj.ooo.kkk.eer.util

import com.jjjj.ooo.kkk.eer.appContext
import com.onesignal.OneSignal

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "9336b566-044e-4326-86ac-21c5136d73e8"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}