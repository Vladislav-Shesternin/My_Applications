package com.jettylucketjet1wincasino.onewinslots1win.util

import com.onesignal.OneSignal
import com.jettylucketjet1wincasino.onewinslots1win.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "8af74c97-3f01-4c0d-ad68-4efdc2ba9bf8"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}