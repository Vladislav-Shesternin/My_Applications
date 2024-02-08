package com.vi.bt.online.util

import com.onesignal.OneSignal
import com.vi.bt.online.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "8c0f55f6-2782-4c2c-ae8f-1d913ffa6a31"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}