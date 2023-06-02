package com.plugoya.rosgpb.util

import com.onesignal.OneSignal
import com.plugoya.rosgpb.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "b77f90a8-ab5d-479e-9692-58e8a381e944"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}