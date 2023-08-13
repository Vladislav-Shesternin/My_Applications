package com.mariam.cleverfinancier.util

import com.onesignal.OneSignal
import com.mariam.cleverfinancier.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "39f09f3a-f93f-4fcc-847b-0efec7b31595"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}