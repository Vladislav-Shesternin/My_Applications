package com.vbt.game.sptr.util

import com.onesignal.OneSignal
import com.vbt.game.sptr.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "a04332f7-8265-4255-ab42-d2b31de826cd"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}