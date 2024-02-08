package com.zet.vest.app.util

import com.onesignal.OneSignal
import com.zet.vest.app.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "0ba8e791-9df6-4a48-b31c-984ab5e75d4e"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}