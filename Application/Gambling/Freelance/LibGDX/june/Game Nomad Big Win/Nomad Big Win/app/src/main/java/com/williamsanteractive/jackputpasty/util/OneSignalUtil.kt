package com.williamsanteractive.jackputpasty.util

import com.onesignal.OneSignal
import com.williamsanteractive.jackputpasty.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "744972a9-2c9b-468f-8b89-79966b74b948"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}