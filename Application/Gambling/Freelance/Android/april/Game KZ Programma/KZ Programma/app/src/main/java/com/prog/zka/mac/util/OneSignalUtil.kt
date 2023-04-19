package com.prog.zka.mac.util

import com.onesignal.OneSignal
import com.prog.zka.mac.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "41cf6508-86c1-492d-9c5c-b2967db0e5e2"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}