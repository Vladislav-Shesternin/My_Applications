package com.vbbb.time.game.util

import com.onesignal.OneSignal
import com.vbbb.time.game.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "c87f92d9-68fe-4aa6-a45b-72057fe251a8"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}