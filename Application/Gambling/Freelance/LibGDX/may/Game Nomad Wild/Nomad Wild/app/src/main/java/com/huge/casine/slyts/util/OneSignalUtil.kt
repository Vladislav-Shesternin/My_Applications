package com.huge.casine.slyts.util

import com.onesignal.OneSignal
import com.huge.casine.slyts.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "9786b688-b8b0-4faf-8011-2106f7acda31"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}