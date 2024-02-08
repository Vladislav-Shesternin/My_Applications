package com.vbtb.game.util

import com.onesignal.OneSignal
import com.vbtb.game.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "5d854b43-bed1-4490-88ad-de38a3c137fe"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}