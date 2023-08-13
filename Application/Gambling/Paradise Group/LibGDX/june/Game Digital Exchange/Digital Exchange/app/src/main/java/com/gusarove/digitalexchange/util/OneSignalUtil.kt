package com.gusarove.digitalexchange.util

import com.onesignal.OneSignal
import com.gusarove.digitalexchange.appContext

object OneSignalUtil {

    private const val ONESIGNAL_APP_ID = "b0ee5db7-7110-44db-9675-b6cda40d3306"

    fun initialize() {
        log("OneSignal initialize")

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(appContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}